package test.lg;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.reachability.Reachability;
import lg.reachability.ReachabilityRule;
import lg.reachability.ReachabilityTable;
import lg.reachability.ReachabilityTableGenerator;

import org.junit.Before;
import org.junit.Test;

import test.MockData;

// TODO replace using MockDataFactory
public class TestReachabilityTable {
  
  AbstractBoardGame abg;
  Location location;
  
  @Before
  public void initialize(){
    abg = MockData.abstractBoardGame();
    location = MockData.centerLocation();
  }

  @Test
  public void testReachabilityTableCase1(){
    
    Reachability reach = new Reachability();
    Piece piece = new Piece( "Test", Color.BLACK, 1, reach );
    
    
    ReachabilityTable r_table = ReachabilityTableGenerator.generate( abg, piece, location );
    
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getTable()[x][y][z] == 0 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getTable()[x][y][z] == 1 );       
          }
        }
      }
    }
  }
  
  @Test
  public void testReachabilityTableCase2(){
    Reachability reach = new Reachability();
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( Math.abs(x.getX() - y.getX()) <= 1 &&  
                Math.abs(x.getY() - y.getY()) <= 1 &&  
                Math.abs(x.getZ() - y.getZ()) <= 1 );
      }
    });
    Piece piece = new Piece( "Test", Color.BLACK, 1, reach );
    
    ReachabilityTable r_table = ReachabilityTableGenerator.generate( abg, piece, location );
    
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          if( x == MockData.CENTER && y == MockData.CENTER && z == MockData.CENTER ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getTable()[x][y][z] == 0 );
          } else if(  Math.abs(MockData.CENTER - x) == 1 && 
                      Math.abs(MockData.CENTER - y) == 1 && 
                      Math.abs(MockData.CENTER - z) == 1 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getTable()[x][y][z] == 1 );       
          } else if(  Math.abs(MockData.CENTER - x) == 2 && 
                      Math.abs(MockData.CENTER - y) == 2 && 
                      Math.abs(MockData.CENTER - z) == 2 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getTable()[x][y][z],
                        r_table.getTable()[x][y][z] == 2 );       
          }
        }
      }
    }
  }

}
