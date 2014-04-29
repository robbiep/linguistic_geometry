package lg.grammar;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.translations.Transition;
import lg.data_objects.translations.Translation;
import lg.data_objects.translations.Translations;
import lg.data_objects.zone.ZoneBundle;
import lg.data_objects.zone.ZoneTrajectory;

public class G_RS {
  
  private AbstractBoardGame abg;
  private Piece p0;
  private Piece q;

  private Translations translations;
  
  private int SIGN = 1;
  private int END  = 1;
  private int d = 0;
  private Location x0;
  private Location y0;
  private int totalSize = 0;
  
  
  public G_RS( AbstractBoardGame abg, Piece p0, Piece q, Location x, Location y ){
    this.abg = abg;
    this.p0 = p0;
    this.q = q;
    this.x0 = x;
    this.y0 = y;
    translations = new Translations();
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
    G_Z gz = new G_Z( abg, p0, q );
    ZoneBundle zoneBundle = gz.executeGrammar();
    if( totalSize == 0 ){
      totalSize  = zoneBundle.totalSize();
    }
    if( zoneBundle.size() > 0 && translations.size() < totalSize ){
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
      next_translation.transition = NEWMOVE( zoneBundle );
      abg.abg_TR( next_translation.transition );
      translations.put( i, this_translation );
      translations.put( END, next_translation );
      ++ END;
      ++ d;
      SIGN = SIGN * -1;
      A2( END );
      pi( END );
      A2( END );
    } else {
      A3( i );
    }
    
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

  private void A3( int i ){
    if( d > 0 ){
      SIGN = -1 * SIGN;
      //d = d - 1;
    }
  }

  private void pi( int i ){
    // TODO Auto-generated method stub
    
  }

}
