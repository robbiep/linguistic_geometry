package lg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.reachability.ReachabiltyTableGenerator;

public class AbstractBoardGame {
  
  AbstractBoard abstract_board;
  ReachabiltyTableGenerator table_generator;
  HashMap<Piece,Location> pieces;

  private final Integer DEFAULT_DIM = 15;
  
  // TODO remove
  public AbstractBoardGame() {
    super();
    this.abstract_board = new AbstractBoard( DEFAULT_DIM, DEFAULT_DIM, DEFAULT_DIM );
    this.table_generator = new ReachabiltyTableGenerator( abstract_board );
    pieces = new HashMap<Piece,Location>();
  }
  
  public AbstractBoardGame( AbstractBoard ab ) {
    super();
    this.abstract_board = ab;
    this.table_generator = new ReachabiltyTableGenerator( ab );
    pieces = new HashMap<Piece,Location>();
  }

  public AbstractBoard getAbstractBoard(){
    return abstract_board;
  }

  /**
   * Sets new abstract board. Removes any pieces no longer in range.
   * @param abstract_board
   */
  public void setAbstractBoard( AbstractBoard abstract_board ){
    this.abstract_board = abstract_board;
    
    // Remove out of range pieces
    Iterator<Map.Entry<Piece,Location>> it = pieces.entrySet().iterator();
    while( it.hasNext() ){
      if( !abstract_board.validLocation( it.next().getValue() )){
        it.remove();
      }
    }
  }
  
  public void addPiece( Piece piece, Location location ){
    pieces.put( piece, location );
  }
  
  public void removePiece( Piece piece ){
    pieces.remove( piece );
  }
  
  public void clearPieces(){
    pieces.clear();
  }
  
  /**
   * Generates an x by y by z reachability table for a piece
   * @param location location 0 for reachability table
   */
  public void getReachabilityTable( Piece piece, Location location ){
    table_generator.generateReachablityTable( piece, location );
    table_generator.printReachabilityTable();
  }
  
  public Boolean ( Location location ){
    
  }
  
  
  // ABSTRACT BOARD GAME FUNCTIONS //
  
  public Boolean abg_R( Piece piece, Location origin, Location target ){
    return  piece.isReachable( origin, target ) && 
            abstract_board.validLocation( location ) && 
            ;
  }
  
  public Location abg_on( Piece piece ){
    return pieces.get( piece );
  }
  
  public Integer abg_map( Piece piece, Location location ){
    // TODO finish
  }
  

}
