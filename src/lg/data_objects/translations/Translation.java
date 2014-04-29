package lg.data_objects.translations;

import lg.data_objects.location.Location;

public class Translation {
  public int child;
  public int parent;
  public int sibling;
  public Transition transition;
  
  @Override
  public boolean equals( Object object ){
    if (object != null && object instanceof Translation ){
      Translation translation = (Translation) object;
      return this.transition.equals( translation );
    } else {
      return false;
    }
  }
  
}
