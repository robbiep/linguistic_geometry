package lg;

public class Piece {
  
  private String name;
  private int value;
  private Reachability reachability;
  
  // Constructors
  public Piece( String name, int value, Reachability reachability ){
    this.name = name;
    this.value = value;
    this.reachability = reachability;
  }
  
  // Methods
  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }
  
  public Boolean isReachable( Location x, Location y ){
    return reachability.isReachable(x, y);
  }
  
}
