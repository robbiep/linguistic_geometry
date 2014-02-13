package lg;

/**
 *  Represents a discrete location in 3-dimensional space
 */
public class Location {
  
  public Integer x;
  public Integer y;
  public Integer z;
  
  public Location(Integer x) {
    super();
    this.x = x;
    this.y = 1;
    this.z = 1;
  }
  
  public Location(Integer x, Integer y) {
    super();
    this.x = x;
    this.y = y;
    this.z = 1;
  }
  
  public Location(Integer x, Integer y, Integer z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @Override
  public boolean equals(Object object){
    
    if (object != null && object instanceof Location){
      Location location = (Location) object;
      return( this.x == location.x &&
              this.y == location.y &&
              this.z == location.z );
    } else {
      return false;
    }
  }
}
