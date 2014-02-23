package lg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.data_structures.GameMap;
import lg.data_structures.GamePiece;
import lg.reachability.ReachabilityTable;
import lg.reachability.ReachabilityTableGenerator;

public class AbstractBoardGame implements ABG_Functions {
  
  AbstractBoard abstract_board;
  ReachabilityTableGenerator table_generator;
  GameMap gameMap;
  
  public AbstractBoardGame( AbstractBoard ab ) {
    this.abstract_board = ab;
    gameMap = new GameMap();
  }

  public AbstractBoard getAbstractBoard(){
    return abstract_board;
  }
  
  public Integer getDimX(){
    return abstract_board.getX();
  }
  
  public Integer getDimY(){
    return abstract_board.getY();
  }
  
  public Integer getDimZ(){
    return abstract_board.getZ();
  }

  /**
   * Sets new abstract board. Removes any gameMap no longer in range.
   * @param abstract_board
   */
  public void setAbstractBoard( AbstractBoard abstract_board ){
    this.abstract_board = abstract_board;
    
    // Remove out of range gameMap
    Iterator<Map.Entry<Piece,Location>> it = gameMap.entrySet().iterator();
    while( it.hasNext() ){
      if( !abstract_board.validLocation( it.next().getValue() )){
        it.remove();
      }
    }
  }
  
  ///////////////////////////////////////////
  // GamePiece FUNCTIONS
  ///////////////////////////////////////////
  
  public GamePiece getByPiece( Piece piece ){
    return gameMap.getByPiece( piece );
  }
  
  public GamePiece getByLocation( Location location ){
    return gameMap.getByLocation( location );
  }
  
  public GamePiece[] getAllByColor( Color color ){
    ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
    Iterator<Map.Entry<Piece,Location>> it = gameMap.entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Piece,Location> entry = it.next();
      if( entry.getKey().getColor() == color ){
        gamePieces.add( new GamePiece( entry.getKey(), entry.getValue() )); 
      }
    }
    return (GamePiece[]) gamePieces.toArray();
  }
  
  public void clearAllByColor( Color color ){
    Iterator<Map.Entry<Piece,Location>> it = gameMap.entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Piece,Location> entry = it.next();
      if( entry.getKey().getColor() == color ){
        it.remove();
      }
    }
  }
  
  public void addPiece( Piece piece, Location location ){
    gameMap.put( piece, location );
  }
  
  public void removePiece( Piece piece ){
    gameMap.remove( piece );
  }
  
  public void clearPieces(){
    gameMap.clear();
  }
 
  public Boolean validLocation( Location location ){
    return( abstract_board.validLocation( location ) );
  }
  
  /**
   * Generates an x by y by z reachability table for a piece
   * @param location location 0 for reachability table
   */
  public ReachabilityTable getReachabilityTable( Piece piece, Location location ){
    return ReachabilityTableGenerator.generate( this, piece, location );
  }
  

  
  ///////////////////////////////////
  // ABSTRACT BOARD GAME FUNCTIONS //
  ///////////////////////////////////
  
  @Override
  public Boolean abg_R( Piece piece, Location origin, Location target ){
    return  piece.isReachable( origin, target ) && 
            validLocation( target );
  }

  @Override
  public Location abg_ON( Piece piece ){
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer abg_TR( Piece piece, Location origin, Location target ){
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer abg_MAP( Piece piece, Location location ){
    // TODO Auto-generated method stub
    return null;
  }
  

}
