package lg.reachability;

import java.util.ArrayList;
import java.util.HashSet;

import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_structures.GamePiece;

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
  private static HashSet<Location> blocked_locations;
  private static AbstractBoardGame abg;
  
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
        updateBlockedLocations( piece, current_location );
        storeLastTable();
        updateReachableLocations( piece, current_location );
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
    Reachability.abg = abg;
    x_dim = abg.getDimX();
    y_dim = abg.getDimY();
    z_dim = abg.getDimZ();
    reachable_location_found = abg.validLocation( location_x );
    initializeTable( x_dim, y_dim, z_dim );
    last_reachablility_table = new Integer[x_dim][y_dim][z_dim];
    reached_locations = new ArrayList<Location>();
    temp_reached_locations = new ArrayList<Location>();
    blocked_locations = new HashSet<Location>();
  }
  
  private static void loadStartLocation( Location location_x ){
    reached_locations.add( location_x );
    reachablility_table[location_x.getX()][location_x.getY()][location_x.getZ()] 
        = distance;
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
          last_reachablility_table[x][y][z] 
              = new Integer(reachablility_table[x][y][z]);
        }
      }
    }
  }
  
  private static void updateReachableLocations( Piece piece, 
                                                Location current_location ){
    for( int x = 0; x < x_dim; ++ x ){
      for( int y = 0; y < y_dim; ++ y ){
        for( int z = 0; z < z_dim; ++ z ){
          updateReachableLocation(  piece, 
                                    current_location, 
                                    new Location( x, y, z ) );
        }
      }
    }
  }
  
  // TODO only works for straight lines, not irregular paths
  private static void updateBlockedLocations( Piece piece, 
                                              Location current_location ){
    blocked_locations.clear();
    ArrayList<GamePiece> game_pieces = abg.getAllPiecesByColor( Color.OBSTACLE );
        
    for( int i = 0; i < game_pieces.size(); ++ i ){
      
      Location blocked_location = game_pieces.get(i).location;
      blocked_locations.add( blocked_location );
      
      int x_inc = incValue( blocked_location.getX() - current_location.getX() );
      int y_inc = incValue( blocked_location.getY() - current_location.getY() );
      int z_inc = incValue( blocked_location.getZ() - current_location.getZ() );
      
      if( x_inc == 0 && y_inc == 0 && z_inc == 0){
        return;
      }
      
      int next_x = blocked_location.getX() + x_inc;
      int next_y = blocked_location.getY() + y_inc;
      int next_z = blocked_location.getZ() + z_inc;
      
      while( next_x >= 0 && next_y >= 0 && next_z >= 0 &&
          next_x < x_dim && next_y < y_dim && next_z < z_dim ){
        blocked_locations.add( new Location(  next_x, next_y, next_z ) );
       // revertReachability( next_x, next_y, next_z );
        next_x = next_x + x_inc;
        next_y = next_y + y_inc;
        next_z = next_z + z_inc;
      }
    }
  }
  
  private static int incValue( int i ){
    if( i > 0 ){
      return 1;
    } else  if( i < 0 ){
      return -1;
    } else {
      return 0;
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
      setTableReach( next_location );
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
