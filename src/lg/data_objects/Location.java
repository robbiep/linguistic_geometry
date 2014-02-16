package lg.data_objects;

/**
 *  Represents a discrete location in 3-dimensional space
 */
public class Location {
  
  private Integer x;
  private Integer y;
  private Integer z;
  
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
  
  public Integer getX(){
    return x;
  }

  public void setX( Integer x ){
    this.x = x;
  }

  public Integer getY(){
    return y;
  }

  public void setY( Integer y ){
    this.y = y;
  }

  public Integer getZ(){
    return z;
  }

  public void setZ( Integer z ){
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
