package lg.reachability;

import java.util.ArrayList;

import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_objects.Piece;

/**
 * This describes the reachability of an LG piece within an abstract board game.
 */
public class Reachability {
  
  private static Integer distance;
  private static Integer x_dim;
  private static Integer y_dim;
  private static Integer z_dim;
  private static Boolean reachable_location_found;
  private static Integer[][][] reachablility_table;
  private static Integer[][][] last_reachablility_table;
  private static ArrayList<Location> reached_locations;
  private static ArrayList<Location> temp_reached_locations;
  private static ArrayList<Location> blocked_locations;
  
  // TODO create efficient version
  /**
   * @return Returns the distance for piece to move from x to y. Equivalent to abg_MAP
   */
  public static Integer getDistance(  AbstractBoardGame abg, 
                                      Piece piece, 
                                      Location location_x, 
                                      Location location_y ){
    return generateTable( abg, piece, location_x ).getValue( location_y );
  }
  
  /**
   * Creates a x by y by z reachability table for a piece
   * @param ab - abstract board provides the dimensions for drawing
   * @param piece - piece to generate the table for
   * @param location_x - initial position to create the table from
   * @return the reachability table
   */
  public static ReachabilityTable generateTable(  AbstractBoardGame abg, 
                                                  Piece piece, 
                                                  Location location_x ){
    initialize( abg, location_x );
    if( !reachable_location_found ){
      return null;
    }
    loadStartLocation( location_x );
    
    while( searchIncomplete() ){
      iterationUpdate();
      for( Location current_location : reached_locations ){
        blocked_locations = new ArrayList<Location>();
        storeLastTable();
        for( int x = 0; x < x_dim; ++ x ){
          for( int y = 0; y < y_dim; ++ y ){
            for( int z = 0; z < z_dim; ++ z ){
              Location next_location = new Location( x, y, z );
              if( !abg.emptyLocation( next_location ) ){
                updateBlockedLocations( current_location, next_location );
              }
              updateReachableLocation( piece, current_location, next_location );
            }
          }
        }
      }
      reached_locations = new ArrayList<Location>(temp_reached_locations);
    }
    return new ReachabilityTable( piece, reachablility_table );
  }
 
  /////////////////////////////////////////////////////////////////////////////
  //  Private Methods
  


  private static void initializeTable( Integer x_dim, Integer y_dim, Integer z_dim ){
    reachablility_table = new Integer[x_dim][y_dim][z_dim]; 
    for( int x = 0; x < x_dim; ++ x ){
      for( int y = 0; y < y_dim; ++ y ){
        for( int z = 0; z < z_dim; ++ z ){
          reachablility_table[x][y][z] = ReachabilityTable.INFINITY;
        }
      }
    }
  }
  
  private static void initialize( AbstractBoardGame abg, Location location_x ){
    distance = 0;
    x_dim = abg.getDimX();
    y_dim = abg.getDimY();
    z_dim = abg.getDimZ();
    reachable_location_found = abg.validLocation( location_x );
    initializeTable( x_dim, y_dim, z_dim );
    last_reachablility_table = new Integer[x_dim][y_dim][z_dim];
    reached_locations = new ArrayList<Location>();
    temp_reached_locations = new ArrayList<Location>();
  }
  
  private static void loadStartLocation( Location location_x ){
    reached_locations.add( location_x );
    reachablility_table[location_x.getX()][location_x.getY()][location_x.getZ()] = distance;
  }
  
  private static boolean searchIncomplete(){
    return reachable_location_found && distance < ReachabilityTable.INFINITY;
  }
  
  private static void iterationUpdate(){
    ++ distance;
    reachable_location_found = false;
    temp_reached_locations.clear();
  }
  
  private static void storeLastTable(){
    for( int x = 0; x < x_dim; ++ x ){
      for( int y = 0; y < y_dim; ++ y ){
        for( int z = 0; z < z_dim; ++ z ){
          last_reachablility_table[x][y][z] = new Integer(reachablility_table[x][y][z]);
        }
      }
    }
  }
  
  private static void updateBlockedLocations( Location current_location,
                                              Location next_location ){
    if( !current_location.getX().equals( next_location.getX() )){
      int increment = ( next_location.getX() > current_location.getX() ) ? 1:-1;
      for(  int temp_x = next_location.getX(); 
            temp_x < x_dim && temp_x >= 0; 
            temp_x += increment ){
        blocked_locations.add( new Location(  temp_x, 
                                              next_location.getY(), 
                                              next_location.getZ() ) );
        revertReachability(temp_x, next_location.getY(), next_location.getZ());
      }
    }
    if( !current_location.getY().equals( next_location.getY() )){
      int increment = ( next_location.getY() > current_location.getY() ) ? 1:-1;
      for(  int temp_y = next_location.getY(); 
            temp_y < y_dim && temp_y >= 0; 
            temp_y += increment ){
        blocked_locations.add( new Location(  next_location.getX(), 
                                              temp_y, 
                                              next_location.getZ() ) );
        revertReachability( next_location.getX(), temp_y, next_location.getZ());
      }               
    }
    if( !current_location.getZ().equals( next_location.getZ() ) ){
      int increment = ( next_location.getZ() > current_location.getZ() ) ? 1:-1;
      for(  int temp_z = next_location.getZ(); 
            temp_z < z_dim && temp_z >= 0; 
            temp_z += increment ){
        blocked_locations.add( new Location(  next_location.getX(), 
                                              next_location.getY(), 
                                              temp_z ) );
        revertReachability( next_location.getX(), next_location.getY(), temp_z);
      }
    }
  }
  
  private static void revertReachability( int x, int y, int z ){
    reachablility_table[x][y][z] = new Integer(last_reachablility_table[x][y][z]);
  }
  
  private static void updateReachableLocation(  Piece piece,
                                                Location current_location, 
                                                Location next_location ){
    if( piece.isReachable( current_location, next_location ) && 
        getTableReach( next_location ) > distance &&
        !blocked_locations.contains( next_location ) ){
      setTableReach (next_location );
      reachable_location_found = true;
      temp_reached_locations.add( next_location );
    }
  }

  private static Integer getTableReach( Location next_location ){
    return reachablility_table[next_location.getX()][next_location.getY()][next_location.getZ()];
  }
  private static void setTableReach( Location next_location ){
    reachablility_table[next_location.getX()][next_location.getY()][next_location.getZ()] = distance;
  }
  
}
