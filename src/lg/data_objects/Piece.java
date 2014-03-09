package lg.data_objects;

import lg.reachability.ReachabilityRules;

/**
 * LG game piece
 */
public class Piece {

  private String name;
  private Color color;
  private Integer value;
  private ReachabilityRules reach;
  
  // Constructors
  public Piece( String name, Color color, int value, ReachabilityRules reach ){
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
  
  public ReachabilityRules getReach(){
    return reach;
  }
  
  public void setReach( ReachabilityRules reach ){
    this.reach = reach;
  }

  public void setName( String name ){
    this.name = name;
  }

  public void setColor( Color color ){
    this.color = color;
  }

  public void setValue( Integer value ){
    this.value = value;
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
  
  @Override
  public String toString(){
    return "(" + getName() + ", " + getColor() + ", " + getValue() + ")";
  }
  
}
