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
      if( this.transition != null && translation.transition != null ){
        return this.transition.equals( translation );
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
  
  @Override
  public String toString(){
    if( transition == null ){
      return "";
    } else {
      return "( " + transition.toString()
          + ", PARENT=" + parent 
          + ", CHILD=" + child 
          + ", SIBLING=" + sibling + " )";
    }
  }
  
}
