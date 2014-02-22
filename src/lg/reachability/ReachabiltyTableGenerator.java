package lg.reachability;

import java.util.ArrayList;

import lg.AbstractBoard;
import lg.data_objects.Location;
import lg.data_objects.Piece;

/**
 * This describes the reachability of an LG piece within an abstract board game.
 */
public class ReachabiltyTableGenerator {

  private AbstractBoard ab;
  private Integer reachablility_table[][][];
  private final Integer INFINITY = Integer.MAX_VALUE;

  public ReachabiltyTableGenerator( AbstractBoard ab ) {
    this.ab = ab;
    reachablility_table = new Integer[ab.getX()][ab.getY()][ab.getZ()];
    clearReachabilityTable();
  }
  
  private void clearReachabilityTable(){
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          reachablility_table[x][y][z] = INFINITY;
        }
      }
    }
  }
  
  /**
   * Creates a x by y by z reachability table, returns it, and stores it within the class
   * @param piece - piece to generate the table for
   * @param location_x - initial position to create the table from
   * @return the reachability table
   */
  public ReachabilityTable generateReachablityTable( Piece piece, Location location_x ){
    clearReachabilityTable();
    if( !ab.validLocation( location_x ) ){
      return null;
    }
    
    Integer distance = 0;
    Boolean reachable_location_found = true;
    ArrayList<Location> reached_locations = new ArrayList<Location>();
    ArrayList<Location> temp_reached_locations = new ArrayList<Location>();
    
    reached_locations.add( location_x );
    reachablility_table[location_x.getX()][location_x.getY()][location_x.getZ()] = distance;
    
    while( reachable_location_found && distance < INFINITY ){
      ++ distance;
      reachable_location_found = false;
      temp_reached_locations.clear();
      for( Location current_location : reached_locations ){
        for( int x = 0; x < ab.getX(); ++ x ){
          for( int y = 0; y < ab.getY(); ++ y ){
            for( int z = 0; z < ab.getZ(); ++ z ){
              
               Location next_location = new Location( x, y, z );
              if( piece.isReachable( current_location, next_location ) && 
                  reachablility_table[x][y][z] > distance &&
                  ab.validLocation( next_location ) ){
                reachablility_table[x][y][z] = distance;
                reachable_location_found = true;
                temp_reached_locations.add( next_location );
              }
            }
          }
        }
      }
      reached_locations = new ArrayList<Location>(temp_reached_locations);
    }
    return new ReachabilityTable( piece, reachablility_table );
  }

  public Integer[][][] getReachability_table(){
    return reachablility_table;
  }
  
  public void printReachabilityTable(){
    for( int z = 0; z < ab.getZ(); ++ z ){
      printTwoDimTable( z );
      System.out.print( "\n\n" );
    }
  }
  
 /**
  * Prints the 2 dimensional table at a specific offset for dimension Z
  * @param z - offset in dimension z
  */
  public void printTwoDimTable( Integer z ){
    System.out.println( "Z dimension = " + z );
    for( int y = 0; y < ab.getY(); ++ y ){
      for( int x = 0; x < ab.getX(); ++ x ){
        System.out.print( (reachablility_table[x][y][z].equals( INFINITY )) ? "x " : reachablility_table[x][y][z] + " " );
      }
      System.out.print( "\n" );
    }
  }
  
}
