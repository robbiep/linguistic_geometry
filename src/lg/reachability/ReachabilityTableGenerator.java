package lg.reachability;

import java.util.ArrayList;

import lg.AbstractBoard;
import lg.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_objects.Piece;

/**
 * This describes the reachability of an LG piece within an abstract board game.
 */
public class ReachabilityTableGenerator {
 
  
  /**
   * Creates a x by y by z reachability table for a piece
   * @param ab - abstract board provides the dimensions for drawing
   * @param piece - piece to generate the table for
   * @param location_x - initial position to create the table from
   * @return the reachability table
   */
  public static ReachabilityTable generate( AbstractBoardGame abg, Piece piece, Location location_x ){
    if( !abg.getAbstractBoard().validLocation( location_x ) ){
      return null;
    }
    
    Integer distance = 0;
    Integer x_dim = abg.getDimX();
    Integer y_dim = abg.getDimY();
    Integer z_dim = abg.getDimZ();
    Boolean reachable_location_found = true;
    Integer[][][] reachablility_table = initializeTable( x_dim, y_dim, z_dim );
    ArrayList<Location> reached_locations = new ArrayList<Location>();
    ArrayList<Location> temp_reached_locations = new ArrayList<Location>();
    
    reached_locations.add( location_x );
    reachablility_table[location_x.getX()][location_x.getY()][location_x.getZ()] = 0;
    
    while( reachable_location_found && distance < ReachabilityTable.INFINITY ){
      ++ distance;
      reachable_location_found = false;
      temp_reached_locations.clear();
      
      for( Location current_location : reached_locations ){
        for( int x = 0; x < x_dim; ++ x ){
          for( int y = 0; y < y_dim; ++ y ){
            for( int z = 0; z < z_dim; ++ z ){
               Location next_location = new Location( x, y, z );
              if( piece.isReachable( current_location, next_location ) && 
                  reachablility_table[x][y][z] > distance &&
                  abg.validLocation( next_location ) ){
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
  
  private static Integer[][][] initializeTable( Integer x_dim, Integer y_dim, Integer z_dim ){
    Integer[][][] table = new Integer[x_dim][y_dim][z_dim]; 
    for( int x = 0; x < x_dim; ++ x ){
      for( int y = 0; y < y_dim; ++ y ){
        for( int z = 0; z < z_dim; ++ z ){
          table[x][y][z] = ReachabilityTable.INFINITY;
        }
      }
    }
    return table;
  }
  
}
