package lg;

import java.util.ArrayList;

import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.reachability.ReachabiltyTableGenerator;

public class AbstractBoardGame {
  
  AbstractBoard abstract_board;
  ReachabiltyTableGenerator table_generator;
  ArrayList<Piece> pieces;
  
  public AbstractBoardGame() {
    super();
    this.abstract_board = new AbstractBoard();
    this.table_generator = new ReachabiltyTableGenerator( abstract_board );
    pieces = new ArrayList<Piece>();
  }
  
  public AbstractBoardGame( AbstractBoard ab ) {
    super();
    this.abstract_board = ab;
    this.table_generator = new ReachabiltyTableGenerator( ab );
    pieces = new ArrayList<Piece>();
  }

  public AbstractBoard getAbstractBoard(){
    return abstract_board;
  }

  public void setAbstractBoard( AbstractBoard abstract_board ){
    this.abstract_board = abstract_board;
  }
  
  public void addPiece( Piece piece ){
    pieces.add( piece );
  }
  
  public void clearPieces(){
    pieces.clear();
  }
  
  public void getReachabilityTable( Piece piece, Location location ){
    table_generator.generateReachablityTable( piece, location );
    table_generator.printReachabilityTable();
  }
 

}
