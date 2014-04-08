package lg.data_objects.zone;

import lg.data_objects.location.Location;

/**
 * Tuple that contains a location x that should target location y within
 * atleast l moves.
 */
public class ZoneTarget {
  public Location x;
  public Location y;
  public Integer l;
  
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
}
