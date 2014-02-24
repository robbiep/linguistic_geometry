package lg.abstract_board_game;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public interface ABG_Functions {
  
  public Boolean    abg_R   ( Piece piece, Location origin, Location target );
  public Location   abg_ON  ( Piece piece );
  public Integer    abg_TR  ( Piece piece, Location origin, Location target );
  public Integer    abg_MAP ( Piece piece, Location location );
}
