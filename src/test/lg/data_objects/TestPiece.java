package test.lg.data_objects;

import static org.junit.Assert.*;

import org.junit.Test;

import test.MockData;
import chess.ChessConstants;
import lg.data_objects.Color;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.reachability.ReachabilityRules;



public class TestPiece {
  
  @Test
  public void testPieceCtor() {
    Piece piece = new Piece( ChessConstants.NAME_KING, Color.WHITE, ChessConstants.VALUE_KING, new ReachabilityRules() );
    assertEquals( "Set/Get 'name'", "King", piece.getName() );
    assertEquals( "Set/Get 'group'", "White", piece.getColor() );
    assertEquals( "Set/Get 'value'", new Integer(Integer.MAX_VALUE), piece.getValue() );
    assertTrue( "Reachability", piece.isReachable(null, null) );
  }
  
  @Test
  public void testPieceFactoryObstacle(){
    Piece piece = MockData.pieceFactory().createObstacle();
    assertEquals( "Set/Get 'name'", MockData.pieceFactory().OBSTACLE, piece.getName() );
    assertEquals( "Set/Get 'group'", MockData.pieceFactory().COLOR, piece.getColor() );
    assertEquals( "Set/Get 'value'", MockData.pieceFactory().VALUE, piece.getValue() );
    assertTrue( "Reachability", piece.isReachable( null, null ) == null );
  }
  
  @Test
  public void testPieceSetColor(){
    Piece piece = MockData.pieceFactory().createPawn( Color.WHITE );
    piece.setColor( Color.getOpposite( piece.getColor()  ));
    assertTrue( piece.getColor() == Color.BLACK );
  }
}
