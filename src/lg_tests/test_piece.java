package lg_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import lg.Piece;
import lg.Reachability;



public class test_piece {
  
  @Test
  public void testPieceCtor() {
    Piece piece = new Piece( "King", 200, new Reachability() );
    assertEquals( "Set/Get 'name'", "King", piece.getName() );
    assertEquals( "Set/Get 'value'", 200, piece.getValue() );
    assertTrue( "Reachability", piece.isReachable(null, null) );
  }

}
