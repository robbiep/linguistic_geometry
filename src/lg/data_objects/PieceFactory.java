package lg.data_objects;

public class PieceFactory {
  
  public final String   OBSTACLE  = "Obstacle";
  public final Color    COLOR     = Color.OBSTACLE;
  public final Integer  VALUE     = 0;
  
  public PieceFactory(){}
  
  public Piece createObstacle(){
    return new Piece( OBSTACLE, COLOR, VALUE, null );
  }

}
