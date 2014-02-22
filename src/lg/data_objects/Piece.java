package lg.data_objects;

import lg.reachability.Reachability;

/**
 * LG game piece
 */
public class Piece {
  
  private String name;
  private String group;
  private Integer value;
  private Reachability reach;
  
  // Constructors
  public Piece( String name, String group, int value, Reachability reach ){
    this.name = name;
    this.group = group;
    this.value = value;
    this.reach = reach;
  }
  
  // Methods
  public String getName() {
    return name;
  }
  
  public String getGroup() {
    return group;
  }

  public Integer getValue() {
    return value;
  }
  
  public Boolean isReachable( Location x, Location y ){
    if( reach == null ){
      return null;
    }
    return reach.isReachable(x, y);
  }
  
}
