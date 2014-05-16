package lg.grammar;

import java.util.ArrayList;
import java.util.HashMap;

import chess.ChessPieceFactory;
import project.Project4_MV;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.translations.Transition;
import lg.data_objects.translations.Translation;
import lg.data_objects.translations.Translations;
import lg.data_objects.zone.MV;
import lg.data_objects.zone.ZoneBundle;
import lg.data_objects.zone.ZoneTrajectory;
import lg.data_structures.GamePiece;

public class G_RS {
  
  private AbstractBoardGame abg;
  private Piece p0;
  private Piece q;

  private Translations translations;
  
  private ArrayList<Transition> transitions;
  private class DeletedPiece {
    Piece piece;
    Location location;
  }
  private HashMap<Integer,DeletedPiece> deletedPieces;
  private HashMap<Translation,Integer> terminalValues;
  
  
  private int SIGN = 1;
  private int END  = 1;
  private int d = 0;
  private Location x0;
  private Location y0;
  private int totalSize = 0;
  private MV MV;
  
  private String parseTree = "";
  
  
  public G_RS( AbstractBoardGame abg, Piece p0, Piece q, Location x, Location y ){
    this.abg = abg;
    this.p0 = p0;
    this.q = q;
    this.x0 = x;
    this.y0 = y;
    translations = new Translations();
    transitions = new ArrayList<Transition>();
    deletedPieces = new HashMap<Integer,DeletedPiece>();
    terminalValues = new HashMap<Translation, Integer>();
  }
  
  public Translations executeGrammar(){
    return S( 0 );
  }
  
  public Translations S( int i ){
    translations.put( i, new Translation() );
    A2( i );
    return translations;
  }

  private void A2( int i ){
   // G_Z gz = new G_Z( abg, p0, q );
   // ZoneBundle zoneBundle = gz.executeGrammar();
//    if( totalSize == 0 ){
//      totalSize  = zoneBundle.totalSize();
//    }
    if( GET_NEXT() && !GAMEOVER() && !CUT() ){
      Translation next_translation = new Translation();
      next_translation.parent = i;
      Translation this_translation = translations.get( i );
      if( this_translation.child != 0 ){
        Translation sibling_translation = translations.get( this_translation.child );
        sibling_translation.sibling = END;
        translations.put( this_translation.child, sibling_translation );
      } else {
        this_translation.sibling = 0;
        this_translation.child = 0;
      }
      next_translation.transition = MV.getNext();
      GamePiece piece = abg.abg_TR( next_translation.transition );
      transitions.add( next_translation.transition );
      translations.put( i, this_translation );
      translations.put( END, next_translation );
      ++ END;
      ++ d;
      SIGN = SIGN * -1;
      A2( i + 1 );
      pi( i );
      A2( i );
    } else {
      A3( i );
    }
    
  }

  private boolean GET_NEXT(){
    Transition next = MV.seeNext();
    if( next != null ){
      return( abg.getByLocation( next.x ) != null );
    }
    return false;
  }

  private boolean GAMEOVER(){
    ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    Piece whiteKing = chessPieceFactory.createKing( Color.WHITE );
    Piece blackKing = chessPieceFactory.createKing( Color.BLACK );
    Location pawnLocation = abg.getByPiece( chessPieceFactory.createPawn( Color.WHITE ) );
    if( pawnLocation == null ){
      return true;
    }
    if( abg.getDistance( whiteKing, abg.getByPiece( whiteKing ), pawnLocation ) == 1  ){
      return true;
    }
    if( pawnLocation.getY() != 0 ){
      pawnLocation = new Location( pawnLocation.getX(), pawnLocation.getY() - 1, 0 );
    }
    if( abg.getDistance( whiteKing, abg.getByPiece( whiteKing ), pawnLocation ) == 1  ){
      return true;
    }
    Piece blackPawn = chessPieceFactory.createKing( Color.BLACK );
    if( abg.getDistance( blackPawn, abg.getByPiece( blackPawn ), new Location(7,7,0) ) >=
        abg.getDistance( whiteKing, abg.getByPiece( whiteKing ), abg.getByPiece( blackPawn ) ) + 1 ){
      return true;
    }
    if( abg.getDistance( whiteKing, abg.getByPiece( whiteKing ), abg.getByPiece( blackKing ) ) == 1 ){
      return true;
    }

    return false;
  }

  private boolean CUT(){
    return MV.getPosition() >= MV.getSize(); 
  }

  private void A3( int i ){
    reverseState();
    Translation translation = translations.get( d );
    terminalValues.put( translation, SIGN );
    if( d > 0 ){
      SIGN = -1 * SIGN;
      d = d - 1;
    }
  }

  private void reverseState(){
    DeletedPiece deletedPiece = deletedPieces.remove( d );
    Transition transition = null;
    if( transitions.size() >0 ){
      transition = transitions.remove( transitions.size() - 1 );
    }
    if( deletedPiece != null ){
      abg.addPiece( deletedPiece.piece, deletedPiece.location );
    }
    if( transition != null ){
      abg.abg_TR( transition.reverse() );
    }
  }

  private void pi( int i ){
    parseTree += "pi(" + i + ")";
  }
  
  private Transition NEWMOVE( ZoneBundle zoneBundle ){
    ZoneBundle subZone = null;
    if( SIGN == 1 ){
      subZone = zoneBundle.getSubZones( Color.WHITE );
    } else {
      subZone = zoneBundle.getSubZones( Color.BLACK );
    }
    if( subZone.size() > 0 ){
      for( ZoneTrajectory zoneTrajectory : subZone.get(0).getZoneTrajectories() ){
        Piece piece = zoneTrajectory.getTrajectory().getPiece();
        Location current_location = zoneTrajectory.getTrajectory().getTrajectoryPath().get(0);
        Location next_location = zoneTrajectory.getTrajectory().getTrajectoryPath().get(1);
        Transition transition = new Transition( piece, current_location, next_location );
        if( !translations.containsValue(transition) ){
          return transition;
        }
      }
    }
    
    return null;
  }
  
  @Override
  public String toString(){
    return translations.toString();
  }

  public void setMV( MV MV ){
    this.MV = MV;
  }

}
