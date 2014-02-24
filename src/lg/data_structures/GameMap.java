package lg.data_structures;


import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public class GameMap extends HashMap<Location,Piece>{

  public GamePiece getByPiece( Piece piece ){
    Iterator<Map.Entry<Location,Piece>> it = entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Location,Piece> entry = it.next();
      if( entry.getValue().equals( piece ) ){
        return new GamePiece(  piece, entry.getKey() );
      }
    }
    return null;
  }
  
  public GamePiece getByLocation( Location location ){
    Piece piece = get( location );
    if( piece == null ){
      return null;
    }
    return new GamePiece( piece, location );
  }
}
