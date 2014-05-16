package lg.abstract_board_game;

import java.util.Set;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_structures.GamePiece;

public interface ABG_Functions {
  
  /**
   * @return True if a piece can reach the target location
   */
  public Boolean              abg_R   ( Piece piece, 
                                        Location current_location, 
                                        Location target_location );
  /**
   * @return Piece on the Location. Null otherwise.
   */
  public Piece                abg_ON  ( Location location );
  /**
   * @return True if piece successfully moved to new Location
   */
  public GamePiece              abg_TR  ( Piece piece, 
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
   * @param piece TODO
   * @return Set of unoccupied locations a given length from the
   * current location
   */
  public Set<Location>        abg_ST  ( Piece piece, 
                                        Location current_location, 
                                        Integer length );
  
  /**
   * @return Set of all unoccupied locations that connect the 
   * current and target locations of the given length
   */
  public Set<Location>        abg_SUM ( Piece piece,
                                        Location current_location, 
                                        Location target_location,
                                        Integer length );
  
  /**
   * @return Set of locations which fall in the union of:<br>
   * ST( x, 1 ) U ST( x0, length_0 - length + 1) U SUM( p, x0, y0, l0 )
   */
  public Set<Location>        abg_MOVE( Piece piece,
                                        Location start_location, 
                                        Location target_location,
                                        Location current_location,
                                        Integer total_length,
                                        Integer remaining_length );
  
  /**
   * @return Set of locations where:<br>
   * MAP( p, x0, v ) + MAP( p, y0, v ) = l
   */
  public Set<Location>        abg_DOCK( Piece piece,
                                        Location current_location, 
                                        Location target_location,
                                        Integer length );
  
  /**
   * @return True if the pieces are opposing teams. Obstacles are not opposing.
   */
  public Boolean              abg_OPPOSE( Piece piece, Piece piece2 );
}
