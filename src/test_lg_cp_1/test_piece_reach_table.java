package test_lg_cp_1;

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
  public Integer CENTER = 7;
  public AbstractBoard ab;
  public ChessPieceFactory chessPieceFactory;
  ReachabiltyTable r_table;
  Piece piece;
  Location central_location;
  
  public Integer minDistance( Integer a, Integer b ){
    return ( a <= b ) ? b - a : a - b;
  }
  
  @Before
  public void initialize(){
    ab = new AbstractBoard( DIM, DIM, DIM );
    chessPieceFactory = new ChessPieceFactory( ab );
    r_table = new ReachabiltyTable( ab );
    central_location = new Location( CENTER, CENTER, CENTER );
  }

  @Test
  public void testPawnReach(){
    
    piece = chessPieceFactory.createPawn( ChessConstants.COLOR_BLACK );
    r_table.generateReachablityTable( piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == CENTER && CENTER - y >= 0 && z == CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachablility_table()[x][y][z] == CENTER - y );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getReachablility_table()[x][y][z] + " != " + ChessConstants.INFINITY,
                        r_table.getReachablility_table()[x][y][z].equals( ChessConstants.INFINITY ) );       
          }
        }
      }
    }
    
    piece = chessPieceFactory.createPawn( ChessConstants.COLOR_WHITE );
    r_table.generateReachablityTable( piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == CENTER && y - CENTER >= 0 && z == CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachablility_table()[x][y][z] == y - CENTER );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getReachablility_table()[x][y][z] + " != " + ChessConstants.INFINITY,
                        r_table.getReachablility_table()[x][y][z].equals( ChessConstants.INFINITY ) );       
          }
        }
      }
    }
  }
  
  @Test
  public void testRookReach(){
    
    piece = chessPieceFactory.createRook( ChessConstants.COLOR_BLACK );
    r_table.generateReachablityTable( piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( (x == CENTER && z == CENTER) ||
              (y == CENTER && z == CENTER) || 
              (x == CENTER && y == CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getReachablility_table()[x][y][z],
                        r_table.getReachablility_table()[x][y][z] == 1 );
          } else if(  (x == CENTER) ||
                      (y == CENTER) || 
                      (z == CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getReachablility_table()[x][y][z],
                        r_table.getReachablility_table()[x][y][z] == 2 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getReachablility_table()[x][y][z],
                        r_table.getReachablility_table()[x][y][z] == 3 );       
          }
        }
      }
    }
  }
}
