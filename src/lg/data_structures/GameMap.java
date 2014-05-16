package lg.data_structures;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class GameMap extends HashMap<Location,Piece>{
  
  /**
   * 
   */
  private static final long serialVersionUID = -1636697773214722259L;

  public ArrayList<GamePiece>getAll(){
    Set<Entry<Location,Piece>> entry_set = entrySet();
    ArrayList<GamePiece> game_pieces = new ArrayList<>();
    Iterator<Entry<Location,Piece>> it = entry_set.iterator();
    int i = 0;
    while( it.hasNext() ){
      Entry<Location,Piece> entry = it.next();
      game_pieces.add(  new GamePiece( entry.getValue(), entry.getKey() ));
    }
    return game_pieces;
  }
  
}
