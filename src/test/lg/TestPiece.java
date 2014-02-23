package test.lg;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.ChessConstants;
import lg.data_objects.Color;
import lg.data_objects.Piece;
import lg.data_objects.PieceFactory;
import lg.reachability.Reachability;



public class TestPiece {
  
  @Test
  public void testPieceCtor() {
    Piece piece = new Piece( ChessConstants.NAME_KING, Color.WHITE, ChessConstants.VALUE_KING, new Reachability() );
    assertEquals( "Set/Get 'name'", "King", piece.getName() );
    assertEquals( "Set/Get 'group'", "White", piece.getColor() );
    assertEquals( "Set/Get 'value'", new Integer(Integer.MAX_VALUE), piece.getValue() );
    assertTrue( "Reachability", piece.isReachable(null, null) );
  }
  
  @Test
  public void testPieceFactoryObstacle(){
    PieceFactory pieceFactory = new PieceFactory();
    Piece piece = pieceFactory.createObstacle();
    assertEquals( "Set/Get 'name'", pieceFactory.OBSTACLE, piece.getName() );
    assertEquals( "Set/Get 'group'", pieceFactory.COLOR, piece.getColor() );
    assertEquals( "Set/Get 'value'", pieceFactory.VALUE, piece.getValue() );
    assertTrue( "Reachability", piece.isReachable( null, null ) == null );
  }

}
