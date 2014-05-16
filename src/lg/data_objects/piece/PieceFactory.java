package lg.data_objects.piece;

import lg.data_objects.Color;

public abstract class PieceFactory {
  
  public final static String   OBSTACLE_NAME    = "Obstacle";
  public final static Color    OBSTACLE_COLOR   = Color.OBSTACLE;
  public final static Integer  OBSTACLE_VALUE   = 0;
  
  public final static String   TARGET_NAME      = "Target";
  public final static Integer  TARGET_VALUE     = 200;
  
  public PieceFactory(){}
  
  public static Piece createObstacle(){
    return new Piece( OBSTACLE_NAME, OBSTACLE_COLOR, OBSTACLE_VALUE, null );
  }
  
  public static Piece createTarget( Color color ){
    return new Piece( TARGET_NAME, color, TARGET_VALUE, null );
  }
  
  public abstract Piece createPiece( String name, Color color );
  
  public Piece createOpposite( Piece piece ){
    return createPiece( 
        piece.getName(), 
        Color.getOpposite( piece.getColor() ));
  }
}
