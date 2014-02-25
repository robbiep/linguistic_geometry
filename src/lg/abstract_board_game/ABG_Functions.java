package lg.abstract_board_game;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public interface ABG_Functions {
  
  /**
   * @return True if a piece can reach the target location
   */
  public Boolean              abg_R   ( Piece piece, 
                                        Location current_location, 
                                        Location target_location );
  /**
   * @return Location of the piece. Null otherwise.
   */
  public Location             abg_ON  ( Piece piece );
  /**
   * @return True if piece successfully moved to new Location
   */
  public Boolean              abg_TR  ( Piece piece, 
                                        Location current_location, 
                                        Location target_location );
  /**
   * @return Number of steps it takes for a piece to reach target
   * location
   */
  public Integer              abg_MAP ( Piece piece, 
                                        Location current_location, 
                                        Location target_location );
  
  /**
   * @return Array of unoccupied locations a given length from the
   * current location
   */
  public Location[]           abg_ST  ( Location current_location, 
                                        Integer length );
  
  /**
   * @return Array of all unoccupied locations that connect the 
   * current and target locations of the given length
   */
  public Location[]           abg_SUM ( Piece piece,
                                        Location current_location, 
                                        Location target_location,
                                        Integer length );
  
  /**
   * @return Array of locations which fall in the union of:<br>
   * ST( x, 1 ) U ST( x0, length_0 - length + 1) U SUM( p, x0, y0, l0 )
   */
  public Location[]           abg_MOVE( Piece piece,
                                        Location start_location, 
                                        Location target_location,
                                        Location current_location,
                                        Integer total_length,
                                        Integer remaining_length );
  
  /**
   * @return Array of locations where:<br>
   * MAP( p, x0, v ) + MAP( p, y0, v ) = l
   */
  public Location[]           abg_DOCK( Piece piece,
                                        Location current_location, 
                                        Location target_location,
                                        Integer length );
}
