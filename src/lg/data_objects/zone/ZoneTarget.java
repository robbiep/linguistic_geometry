package lg.data_objects.zone;

import lg.data_objects.location.Location;

/**
 * Triple tuple that contains a location x that should target 
 * location y within at least l moves.<br>
 * <b>var</b> Location x<br>
 * <b>var</b> Location y<br>
 * <b>var</b> length l<br>
 */
public class ZoneTarget {
  public Location x;
  public Location y;
  public Integer l;
  
  /**
   * Defaults to x = (0,0,0), y = (0,0,0), l = 0
   */
  public ZoneTarget(){
    this.x = new Location();
    this.y = new Location();
    this.l = 0;
  }
  
  public ZoneTarget( Location x, Location y, Integer l ){
    this.x = x;
    this.y = y;
    this.l = l;
  }
  
  @Override
  public String toString(){
    return "(" + x.toString() + ", " + y.toString() + ", " + l + ")";
  }
}
