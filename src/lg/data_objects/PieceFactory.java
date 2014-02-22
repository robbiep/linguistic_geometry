package lg.data_objects;

public class PieceFactory {
  
  private final String OBSTACLE = "Obstacle";
  private final String GROUP = "NULL";
  private final Integer VALUE = 0;
  
  public PieceFactory(){}
  
  public Piece createObstacle(){
    return new Piece( OBSTACLE, GROUP, VALUE, null );
  }

}
