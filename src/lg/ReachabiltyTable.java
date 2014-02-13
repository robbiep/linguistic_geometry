package lg;

import java.util.ArrayList;

/**
 * This describes the reachability of an LG piece within an abstract board game.
 */
public class ReachabiltyTable {

  private AbstractBoard ab;
  private Integer reachablility_table[][][];
  private final Integer INFINITY = Integer.MAX_VALUE;

  public ReachabiltyTable( AbstractBoard ab ) {
    this.ab = ab;
    reachablility_table = new Integer[ab.getX()][ab.getY()][ab.getZ()];
    for( int x = 0; x < ab.getX(); ++ x ){
      for( int y = 0; y < ab.getY(); ++ y ){
        for( int z = 0; z < ab.getZ(); ++ z ){
          reachablility_table[x][y][z] = INFINITY;
        }
      }
    }
  }
  
  public void generateReachablityTable( Piece piece, Location location_x ){
    Integer distance = 0;
    Boolean reachable_location_found = true;
    ArrayList<Location> reached_locations = new ArrayList<Location>();
    ArrayList<Location> temp_reached_locations = new ArrayList<Location>();
    reached_locations.add( location_x );
    reachablility_table[location_x.x][location_x.y][location_x.z] = distance;
    while( reachable_location_found && distance < INFINITY ){
      ++ distance;
      reachable_location_found = false;
      temp_reached_locations.clear();
      for( Location location : reached_locations ){
        for( int x = 0; x < ab.getX(); ++ x ){
          for( int y = 0; y < ab.getY(); ++ y ){
            for( int z = 0; z < ab.getZ(); ++ z ){
              if( piece.isReachable( location, new Location( x, y, z ) ) && 
                  reachablility_table[x][y][z] > distance ){
                reachablility_table[x][y][z] = distance;
                reachable_location_found = true;
                temp_reached_locations.add( new Location( x, y, z ) );
              }
            }
          }
        }
      }
      reached_locations = new ArrayList<Location>(temp_reached_locations);
    }
  }

  public Integer[][][] getReachablility_table(){
    return reachablility_table;
  }
  
}
