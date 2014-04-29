package lg.data_objects.translations;

import java.util.ArrayList;
import java.util.HashMap;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class Translations extends HashMap<Integer,Translation> {

  @Override
  public Translation put( Integer K, Translation V ){
    if( !containsValue( V )){
      return super.put( K, V );
    } else {
      return null;
    }
  }
}
