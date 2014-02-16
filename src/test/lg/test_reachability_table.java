package test.lg;

import static org.junit.Assert.*;
import lg.AbstractBoard;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.reachability.Reachability;
import lg.reachability.ReachabilityRule;
import lg.reachability.ReachabiltyTableGenerator;

import org.junit.Test;

public class test_reachability_table {

  @Test
  public void testReachabilityTableCase1(){
    AbstractBoard ab = new AbstractBoard( 5, 5, 5 );
    Reachability reach = new Reachability();
    Piece piece = new Piece( "Test", "Test", 1, reach );
    
    ReachabiltyTableGenerator r_table = new ReachabiltyTableGenerator( ab );
    r_table.generateReachablityTable( piece, new Location( 3, 3, 3 ) );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == 3 && y == 3 && z == 3 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachability_table()[x][y][z] == 0 );
          } else {
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getReachability_table()[x][y][z] == 1 );       
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
        return( Math.abs(x.getX() - y.getX()) <= 1 &&  
                Math.abs(x.getY() - y.getY()) <= 1 &&  
                Math.abs(x.getZ() - y.getZ()) <= 1 );
      }
    });
    Piece piece = new Piece( "Test", "Test", 1, reach );
    
    ReachabiltyTableGenerator r_table = new ReachabiltyTableGenerator( ab );
    r_table.generateReachablityTable( piece, new Location( 3, 3, 3 ) );
    
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          if( x == 3 && y == 3 && z == 3 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                r_table.getReachability_table()[x][y][z] == 0 );
          } else if(  Math.abs(3 - x) == 1 && 
                      Math.abs(3 - y) == 1 && 
                      Math.abs(3 - z) == 1 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ")",
                        r_table.getReachability_table()[x][y][z] == 1 );       
          } else if(  Math.abs(3 - x) == 2 && 
                      Math.abs(3 - y) == 2 && 
                      Math.abs(3 - z) == 2 ){
            assertTrue( "Fails at (" + x + ", " + y + ", " + z + ") ... " + 
                          r_table.getReachability_table()[x][y][z],
                        r_table.getReachability_table()[x][y][z] == 2 );       
          }
        }
      }
    }
  }

}
