package test_lg;

import static org.junit.Assert.*;
import lg.AbstractBoard;
import lg.Location;
import lg.Piece;
import lg.Reachability;
import lg.ReachabilityRule;
import lg.ReachabiltyTable;

import org.junit.Test;

public class test_reachability_table {

  @Test
  public void testReachabilityTableCase1(){
    AbstractBoard ab = new AbstractBoard( 5, 5, 5 );
    Reachability reach = new Reachability();
    Piece piece = new Piece( "Test", "Test", 1, reach );
    
    ReachabiltyTable r_table = new ReachabiltyTable( ab );
    r_table.generateReachablityTable( piece, new Location( 3, 3, 3 ) );
    
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
  
  @Test
  public void testReachabilityTableCase2(){
    AbstractBoard ab = new AbstractBoard( 5, 5, 5 );
    Reachability reach = new Reachability();
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( Math.abs(x.x - y.x) <= 1 &&  
                Math.abs(x.y - y.y) <= 1 &&  
                Math.abs(x.z - y.z) <= 1 );
      }
    });
    Piece piece = new Piece( "Test", "Test", 1, reach );
    
    ReachabiltyTable r_table = new ReachabiltyTable( ab );
    r_table.generateReachablityTable( piece, new Location( 3, 3, 3 ) );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == 3 && y == 3 && z == 3 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachablility_table()[x][y][z] == 0 );
          } else if(  Math.abs(3 - x) == 1 && 
                      Math.abs(3 - y) == 1 && 
                      Math.abs(3 - z) == 1 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getReachablility_table()[x][y][z] == 1 );       
          } else if(  Math.abs(3 - x) == 2 && 
                      Math.abs(3 - y) == 2 && 
                      Math.abs(3 - z) == 2 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getReachablility_table()[x][y][z],
                        r_table.getReachablility_table()[x][y][z] == 2 );       
          }
        }
      }
    }
  }

}
