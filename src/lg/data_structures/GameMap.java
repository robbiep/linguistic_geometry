package lg.data_structures;


import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public class GameMap extends HashMap<Location,Piece>{
  
  /**
   * 
   */
  private static final long serialVersionUID = -1636697773214722259L;

  public GamePiece[] getAll(){
    Set<Entry<Location,Piece>> entry_set = entrySet();
    GamePiece[] game_pieces = new GamePiece[entry_set.size()];
    Iterator<Entry<Location,Piece>> it = entry_set.iterator();
    int i = 0;
    while( it.hasNext() ){
      Entry<Location,Piece> entry = it.next();
      game_pieces[i] = new GamePiece( entry.getValue(), entry.getKey() );
      ++ i;
    }
    return game_pieces;
  }
  
}
