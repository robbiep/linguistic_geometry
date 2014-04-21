package chess;

import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.reachability.ReachabilityRule;
import lg.reachability.ReachabilityRules;

public class IrregularPieceFactory extends PieceFactory {

  @Override
  public Piece createPiece( String name, Color color ){
    if( name.toLowerCase().equals("irregular") ){
      return createIrregular( color );
    } else {
      return null;
    }
  }
  
  private Piece createIrregular( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    reach.addRule( new ReachabilityRule() {
      
      @Override
      public Boolean rule( Location x, Location y ){
        int direction_modifier = ( color == Color.BLACK ) ? 1 : -1;
        return( (x.getY() + direction_modifier*3 == y.getY() &&
                x.getX() == y.getX() &&
                x.getZ() == y.getZ()) ||
                (x.getY() - direction_modifier == y.getY() &&
                x.getX() == y.getX() &&
                x.getZ() == y.getZ()) ||
                (x.getY() == y.getY() &&
                Math.abs(x.getX() - y.getX()) == 1 &&
                x.getZ() == y.getZ()) ||
                (x.getY() == y.getY() &&
                Math.abs(x.getZ() - y.getZ()) == 1 &&
                x.getX() == y.getX())
                );
      }
    });
    return new Piece( "Irregular", color, 1, reach );
  }


}
