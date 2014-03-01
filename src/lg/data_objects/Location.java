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
  
  /**
   * @return True if current location matches location arg
   */
  public Boolean at( Location location ){
    return at( location.getX(), location.getY(), location.getZ() );
  }
  
  /**
   * @return True if current location matches args
   */
  public Boolean at( int x, int y, int z ){
    return( this.x == x &&
            this.y == y &&
            this.z == z );
  }
  
  @Override
  public String toString(){
    return "(" + x + ", " + y + ", " + z + ")";
  }

  @Override
  public boolean equals(Object object){
    if (object != null && object instanceof Location){
      return at( (Location) object );
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode(){
    return (this.x * 7) + (this.y * 37) + (this.z * 373);
  }
}
