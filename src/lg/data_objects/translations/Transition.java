package lg.data_objects.translations;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class Transition {
  public final Piece piece;
  public final Location x;
  public final Location y;
  
  public Transition(Piece piece, Location x, Location y) {
    this.piece = piece;
    this.x = x;
    this.y = y;
  }
  
  @Override
  public String toString(){
    return "TRANSITION( " + piece.toString() 
        + ", " + x.toString() + ", " + y.toString() + " )";
  }
  
  @Override
  public boolean equals( Object object ){
    if (object != null && object instanceof Transition ){
      Transition transition = (Transition) object;
      return this.piece.equals( transition.piece ) 
          && this.x.equals( transition.x )
          && this.y.equals( transition.y );
    } else {
      return false;
    }
  }

  
}
