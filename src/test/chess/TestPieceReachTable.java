package test.chess;

import static org.junit.Assert.*;

import javax.swing.text.rtf.RTFEditorKit;

import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.reachability.ReachabilityRules;
import lg.reachability.ReachabilityTable;
import lg.reachability.Reachability;

import org.junit.Before;
import org.junit.Test;

import test.MockData;
import chess.ChessConstants;
import chess.ChessPieceFactory;

public class TestPieceReachTable {

  AbstractBoard       ab;
  AbstractBoardGame   abg;
  ChessPieceFactory   chessPieceFactory;
  Piece               piece;
  Location            central_location;
  
  public Integer minDistance( Integer a, Integer b ){
    return ( a <= b ) ? b - a : a - b;
  }
  
  @Before
  public void initialize(){
    ab                = MockData.abstractBoard();
    abg               = MockData.abstractBoardGame();
    chessPieceFactory = MockData.pieceFactory();
    central_location  = MockData.centerLocation();
  }

  @Test
  public void testReachabilityTable(){
    
    Piece obstacle = chessPieceFactory.createObstacle();
    // Instance basic table
    Integer table[][][] = new Integer[MockData.DIMENSION][MockData.DIMENSION][MockData.DIMENSION];
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          table[x][y][z] = 0;
        }
      }
    }
    ReachabilityTable reachabilityTable = new ReachabilityTable( obstacle, table );
    
    // Getters work
    assertTrue( obstacle == reachabilityTable.getPiece() );
    assertTrue( table == reachabilityTable.getTable() );
    
    // Print works
    reachabilityTable.printReachabilityTable();
  }
  
  @Test
  public void testPawnReach(){
    
    piece = chessPieceFactory.createPawn( Color.WHITE );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && MockData.CENTER - y >= 0 && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getTable()[x][y][z] == MockData.CENTER - y );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getTable()[x][y][z] + " != " + ChessConstants.INFINITY,
                        r_table.getTable()[x][y][z].equals( ChessConstants.INFINITY ) );       
          }
        }
      }
    }
    
    piece = chessPieceFactory.createPawn( Color.BLACK );
    r_table = Reachability.generateTable( abg, piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y - MockData.CENTER >= 0 && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getTable()[x][y][z] == y - MockData.CENTER );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getTable()[x][y][z] + " != " + ChessConstants.INFINITY,
                        r_table.getTable()[x][y][z].equals( ChessConstants.INFINITY ) );       
          }
        }
      }
    }
  }
  
  @Test
  public void testRookReach(){
    
    piece = chessPieceFactory.createRook( Color.BLACK );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                r_table.getTable()[x][y][z],
                r_table.getTable()[x][y][z] == 0 );
          } else if( (x == MockData.CENTER && z == MockData.CENTER) ||
              (y == MockData.CENTER && z == MockData.CENTER) || 
              (x == MockData.CENTER && y == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 1 );
          } else if(  (x == MockData.CENTER) ||
                      (y == MockData.CENTER) || 
                      (z == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 2 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 3 );       
          }
        }
      }
    }
  }
  
  @Test
  public void testKnightReach(){
    
    piece = chessPieceFactory.createKnight( Color.BLACK );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    
    // TODO replace with real test
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                r_table.getTable()[x][y][z],
                r_table.getTable()[x][y][z] == 0 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] > 0 );       
          }
        }
      }
    }
  }
  
  @Test
  public void testBishopReach(){
    
    piece = chessPieceFactory.createBishop( Color.BLACK );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    
    // TODO finish test for distance 2 and 3
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                r_table.getTable()[x][y][z],
                r_table.getTable()[x][y][z] == 0 );
          } else if ( (x == y && z == MockData.CENTER) || 
                      (z == y && x == MockData.CENTER) || 
                      (x == z && y == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z]  == 1 );       
          } 
        }
      }
    }
  }
  
  @Test
  public void testQueenReach(){
    
    piece = chessPieceFactory.createQueen( Color.BLACK );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    r_table.printReachabilityTable(7);
    
    // TODO create valid tests
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                r_table.getTable()[x][y][z],
                r_table.getTable()[x][y][z] == 0 );
          } else if ( (x == y && z == MockData.CENTER) || 
                      (z == y && x == MockData.CENTER) || 
                      (x == z && y == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z]  == 1 );       
          } else if(  (x == MockData.CENTER && z == MockData.CENTER) ||
                      (y == MockData.CENTER && z == MockData.CENTER) || 
                      (x == MockData.CENTER && y == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 1 );
          } else if(  (x == MockData.CENTER) ||
                      (y == MockData.CENTER) || 
                      (z == MockData.CENTER) ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 2 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 3 );       
          }
        }
      }
    }
  }
  
  @Test
  public void testKingReach(){
    
    piece = chessPieceFactory.createKing( Color.BLACK );
    ReachabilityTable r_table = Reachability.generateTable( abg, piece, central_location );
    r_table.printReachabilityTable();
    
    // TODO finish test for distance 2 and 3
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                r_table.getTable()[x][y][z],
                r_table.getTable()[x][y][z] == 0 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                        r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] 
                            == Math.max( Math.max( Math.abs(MockData.CENTER - x),Math.abs(MockData.CENTER - y) ), Math.abs(MockData.CENTER - z) ));       
          } 
        }
      }
    }
  }
}
