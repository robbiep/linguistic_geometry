package lg.data_structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public class GameMap extends HashMap<Piece,Location>{
  
  public GamePiece getByPiece( Piece piece ){
    Location location = get( piece );
    if( location == null ){
      return null;
    }
    return new GamePiece( piece, location );
  }
  
  public GamePiece getByLocation( Location location ){
    Iterator<Map.Entry<Piece,Location>> it = entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Piece,Location> entry = it.next();
      if( entry.getValue().equals( location ) ){
        return new GamePiece( entry.getKey(), location );
      }
    }
    return null;
  }
}
