package test_lg;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.ChessConstants;
import lg.data_objects.Piece;
import lg.reachability.Reachability;



public class test_piece {
  
  @Test
  public void testPieceCtor() {
    Piece piece = new Piece( ChessConstants.NAME_KING, ChessConstants.COLOR_WHITE, ChessConstants.VALUE_KING, new Reachability() );
    assertEquals( "Set/Get 'name'", "King", piece.getName() );
    assertEquals( "Set/Get 'group'", "White", piece.getGroup() );
    assertEquals( "Set/Get 'value'", new Integer(200), piece.getValue() );
    assertTrue( "Reachability", piece.isReachable(null, null) );
  }

}
