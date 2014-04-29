package lg.data_objects.translations;

import java.util.ArrayList;
import java.util.HashMap;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class Translations extends HashMap<Integer,Translation> {

  public boolean containsMove( Translation translation ){
    for( Translation _translation : this.values() ){
      if( _translation.equals( translation ) ){
        return true;
      }
    }
    return false;
  }
}
