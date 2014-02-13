package lg;

/**
 * LG game piece
 */
public class Piece {
  
  private String name;
  private String group;
  private Integer value;
  private Reachability reachability;
  
  // Constructors
  public Piece( String name, String group, int value, Reachability reachability ){
    this.name = name;
    this.group = group;
    this.value = value;
    this.reachability = reachability;
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
    return reachability.isReachable(x, y);
  }
  
}
