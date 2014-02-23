package lg.data_objects;

import lg.reachability.Reachability;

/**
 * LG game piece
 */
public class Piece {
  
  private String name;
  private Color color;
  private Integer value;
  private Reachability reach;
  
  // Constructors
  public Piece( String name, Color color, int value, Reachability reach ){
    this.name = name;
    this.color = color;
    this.value = value;
    this.reach = reach;
  }
  
  // Methods
  public String getName() {
    return name;
  }
  
  public Color getColor() {
    return color;
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
  
  @Override
  public boolean equals(Object object){
    
    if (object != null && object instanceof Piece){
      Piece piece = (Piece) object;
      return( this.name.equals( piece.name ) &&
              this.color.equals( piece.color ) &&
              this.value.equals( piece.value) );
    } else {
      return false;
    }
  }
  
}
