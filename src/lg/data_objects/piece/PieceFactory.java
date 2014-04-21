package lg.data_objects.piece;

import lg.data_objects.Color;

public abstract class PieceFactory {
  
  public final static String   OBSTACLE  = "Obstacle";
  public final static Color    COLOR     = Color.OBSTACLE;
  public final static Integer  VALUE     = 0;
  
  public PieceFactory(){}
  
  public static Piece createObstacle(){
    return new Piece( OBSTACLE, COLOR, VALUE, null );
  }
  
  public abstract Piece createPiece( String name, Color color );
  
  public Piece createOpposite( Piece piece ){
    return createPiece( 
        piece.getName(), 
        Color.getOpposite( piece.getColor() ));
  }
}
