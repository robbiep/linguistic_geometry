package test_chess;

import static org.junit.Assert.*;
import lg.AbstractBoard;
import lg.Location;
import lg.Piece;
import lg.Reachability;
import lg.ReachabiltyTable;

import org.junit.Before;
import org.junit.Test;

import chess.ChessConstants;
import chess.ChessPieceFactory;

public class test_piece_reach_table {
  
  public Integer DIM = 15;
  public Integer CENTER = 8;
  public AbstractBoard ab;
  public ChessPieceFactory chessPieceFactory;
  
  @Before
  public void initialize(){
    ab = new AbstractBoard( DIM, DIM, DIM );
    chessPieceFactory = new ChessPieceFactory( ab );
  }

  @Test
  public void testPawnReach(){
    
    Piece piece = chessPieceFactory.createPawn( ChessConstants.COLOR_BLACK );
    
    ReachabiltyTable r_table = new ReachabiltyTable( ab );
    r_table.generateReachablityTable( piece, new Location( CENTER, CENTER, CENTER ) );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == 3 && y == 3 && z == 3 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachablility_table()[x][y][z] == 0 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getReachablility_table()[x][y][z] == 1 );       
          }
        }
      }
    }
  }
}
