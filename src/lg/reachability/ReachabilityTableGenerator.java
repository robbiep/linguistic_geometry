package lg.reachability;

import java.util.ArrayList;

import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
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
      ArrayList<Location> blocked_locations;
      for( Location current_location : reached_locations ){
        blocked_locations = new ArrayList<Location>();
        for( int x = 0; x < x_dim; ++ x ){
          for( int y = 0; y < y_dim; ++ y ){
            for( int z = 0; z < z_dim; ++ z ){
              Location next_location = new Location( x, y, z );
              if( !abg.emptyLocation( next_location ) ){
                blocked_locations.add( next_location );
                if( !current_location.getX().equals( x ) ){
                  int increment = ( x > current_location.getX() ) ? 1 : -1;
                  for( int temp_x = x; temp_x < x_dim && temp_x >= 0; temp_x += increment ){
                    blocked_locations.add( new Location( temp_x, y, z ) );
                  }
                }
                if( !current_location.getY().equals( y ) ){
                  int increment = ( y > current_location.getY() ) ? 1 : -1;
                  for( int temp_y = y; temp_y < y_dim && temp_y >= 0; temp_y += increment ){
                    blocked_locations.add( new Location( x, temp_y, z ) );
                  }               
                }
                if( !current_location.getZ().equals( z ) ){
                  int increment = ( z > current_location.getZ() ) ? 1 : -1;
                  for( int temp_z = z; temp_z < z_dim && temp_z >= 0; temp_z += increment ){
                    blocked_locations.add( new Location( x, y, temp_z ) );
                  }
                }
                  
              }
              if( piece.isReachable( current_location, next_location ) && 
                  reachablility_table[x][y][z] > distance &&
                  abg.validLocation( next_location ) &&
                  !blocked_locations.contains( next_location ) ){
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
  
  // TODO refactor this for efficiency!
  private static boolean validAdjacentVector( 
      AbstractBoardGame abg, 
      Location next_location,
      Integer distance,
      Integer[][][] reachablility_table ){
    int x_start = ( next_location.getX() > 0 ) ? 
        next_location.getX() - 1 : next_location.getX();
    int x_end   = ( next_location.getX() < abg.getDimX() - 1 ) ? 
        next_location.getX() + 1 : next_location.getX();
    int y_start = ( next_location.getY() > 0 ) ? 
        next_location.getY() - 1 : next_location.getY();
    int y_end   = ( next_location.getY() < abg.getDimY() - 1 ) ? 
        next_location.getY() + 1 : next_location.getY();
    int z_start = ( next_location.getZ() > 0 ) ? 
        next_location.getZ() - 1 : next_location.getZ();
    int z_end   = ( next_location.getZ() < abg.getDimZ() - 1 ) ? 
        next_location.getZ() + 1 : next_location.getZ();
    for( int x = x_start; x <= x_end; ++ x ){
      for( int y = y_start; y <= y_end; ++ y ){
        for( int z = z_start; z <= z_end; ++ z ){
          if( reachablility_table[x][y][z] <= distance ){
            return true;
          }
        }
      }
    }
    return false;
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
