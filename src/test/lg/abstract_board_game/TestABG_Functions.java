/**
 * 
 */
package test.lg.abstract_board_game;

import static org.junit.Assert.*;

import java.util.Set;

import lg.abstract_board_game.ABG_Functions;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.reachability.ReachabilityTable;

import org.junit.Before;
import org.junit.Test;

import test.MockData;

/**
 * @author Stank Sauce
 *
 */
public class TestABG_Functions {
  
  AbstractBoardGame abg;
  
  @Before
  public void initialize(){
    abg = MockData.abstractBoardGame();
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_R(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location)}.
   */
  @Test
  public void testAbg_R() {
    abg.addPiece( MockData.pieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
    assertTrue( abg.abg_R(  MockData.pieceFactory().createPawn( Color.WHITE ), 
                            MockData.centerLocation(), 
                            new Location( 7, 6, 7 ) ));
    assertFalse( abg.abg_R( MockData.pieceFactory().createPawn( Color.WHITE ), 
                            MockData.centerLocation(), 
                            new Location( 7, 8, 7 ) ));
    assertTrue( abg.abg_R( MockData.pieceFactory().createPawn( Color.WHITE ), 
                            new Location( 7, 8, 7 ),
                            MockData.centerLocation() ));
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_ON(lg.data_objects.piece.Piece)}.
   */
  @Test
  public void testAbg_ON() {
    abg.addPiece( MockData.pieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
    assertTrue( abg.abg_ON( MockData.centerLocation() ).equals(MockData.pieceFactory().createPawn( Color.WHITE )));
    assertTrue( abg.abg_ON( new Location( 1, 1, 1 ) ) == null );
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_TR(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location)}.
   */
//  @Test
//  public void testAbg_TR() {
//    abg.addPiece( MockData.pieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
//    Location location2 = new Location( 7, 6, 7 );
//    
//    // Move piece to valid empty location
//    assertTrue(
//        abg.abg_TR( MockData.pieceFactory().createPawn( Color.WHITE ), 
//                    MockData.centerLocation(), 
//                    location2 )
//        );
//    assertTrue( abg.abg_ON( location2 ).equals( 
//        MockData.pieceFactory().createPawn( Color.WHITE ) ));
//    
//    // Move piece to non-reachable empty location
//    assertFalse(
//        abg.abg_TR( MockData.pieceFactory().createPawn( Color.WHITE ), 
//                    location2, 
//                    MockData.centerLocation() )
//        );
//    assertTrue( abg.abg_ON( location2 ).equals( 
//        MockData.pieceFactory().createPawn( Color.WHITE ) ));
//    
//    // No piece to move
//    assertFalse(
//        abg.abg_TR( MockData.pieceFactory().createPawn( Color.WHITE ), 
//                    MockData.centerLocation(), 
//                    location2 )
//        );
//    assertTrue( abg.abg_ON( location2 ).equals( 
//        MockData.pieceFactory().createPawn( Color.WHITE ) ));
//    
// // Move piece to enemy location
//    abg.addPiece( MockData.pieceFactory().createQueen( Color.BLACK ), MockData.centerLocation() );
//    assertTrue(
//        abg.abg_TR( MockData.pieceFactory().createQueen( Color.BLACK ), 
//                    MockData.centerLocation(), 
//                    location2 )
//        );
//    assertTrue( abg.abg_ON( location2 ).equals( 
//        MockData.pieceFactory().createQueen( Color.BLACK ) ));
//    
// // Move piece to friendly location
//    abg.addPiece( MockData.pieceFactory().createQueen( Color.BLACK ), MockData.centerLocation() );
//    assertFalse(
//        abg.abg_TR( MockData.pieceFactory().createQueen( Color.BLACK ), 
//                    MockData.centerLocation(), 
//                    location2 )
//        );
//    assertTrue( abg.abg_ON( location2 ).equals( 
//        MockData.pieceFactory().createQueen( Color.BLACK ) ));
//  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_MAP(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location)}.
   */
  @Test
  public void testAbg_MAP() {
    assertTrue(
        abg.abg_MAP(  MockData.pieceFactory().createPawn( Color.WHITE ), 
                      MockData.centerLocation(), 
                      new Location( 7, 0, 7 ) ) == 7 ); 
    assertTrue(
        abg.abg_MAP(  MockData.pieceFactory().createPawn( Color.WHITE ), 
                      MockData.centerLocation(), 
                      new Location( 7, 1, 7 ) ) == 6 ); 
    
    abg.addPiece( MockData.pieceFactory().createPawn( Color.WHITE ),
                  new Location( 7, 1, 7 ) );
    assertTrue(
        abg.abg_MAP(  MockData.pieceFactory().createPawn( Color.WHITE ), 
                      MockData.centerLocation(), 
                      new Location( 7, 0, 7 ) 
        ).equals( ReachabilityTable.INFINITY )); 
    
    assertTrue(
        abg.abg_MAP(  MockData.pieceFactory().createQueen( Color.WHITE ), 
                      MockData.centerLocation(), 
                      new Location( 7, 0, 7 )) == 2); 
    assertTrue(
        abg.abg_MAP(  MockData.pieceFactory().createQueen( Color.WHITE ), 
                      MockData.centerLocation(), 
                      new Location( 7, 14, 7 )) == 1); 
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_ST(Piece, lg.data_objects.location.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_ST() {
    Set<Location> locations = abg.abg_ST( 
        MockData.pieceFactory().createPawn( Color.WHITE ), 
        MockData.centerLocation(), 
        1 );
    assertTrue( locations.size() == 1 );
    assertTrue( locations.contains( new Location( 7, 6, 7 ) ));
    
    locations = abg.abg_ST( 
        MockData.pieceFactory().createPawn( Color.BLACK ), 
        MockData.centerLocation(), 
        1 );
    assertTrue( locations.size() == 1 );
    assertTrue( locations.contains( new Location( 7, 8, 7 ) ));
    
    locations = abg.abg_ST( 
        MockData.pieceFactory().createKing( Color.BLACK ), 
        MockData.centerLocation(), 
        1 );
    assertTrue( locations.size() == 26 );
    assertFalse( locations.contains( new Location( 7, 9, 7 ) ));
    
    locations = abg.abg_ST( 
        MockData.pieceFactory().createKing( Color.BLACK ), 
        MockData.centerLocation(), 
        2 );
    assertTrue( locations.size() == 98 );
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_SUM(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_SUM() {
    Set<Location> locations = abg.abg_SUM( 
        MockData.pieceFactory().createPawn( Color.WHITE ), 
        MockData.centerLocation(), 
        new Location( 7, 5, 7 ),
        2 );
    assertTrue( locations.size() == 3 );
    assertTrue( locations.contains( new Location( 7, 6, 7 ) ));
    
    locations = abg.abg_SUM( 
        MockData.pieceFactory().createPawn( Color.WHITE ), 
        MockData.centerLocation(), 
        new Location( 7, 6, 7 ),
        2 );
    assertTrue( locations.size() == 0 );
    
    locations = abg.abg_SUM( 
        MockData.pieceFactory().createQueen( Color.WHITE ), 
        MockData.centerLocation(), 
        new Location( 7, 6, 7 ),
        1 );
    assertTrue( locations.size() == 2 );
    assertTrue( locations.contains( MockData.centerLocation() ));
    assertTrue( locations.contains( new Location( 7, 6, 7 ) ));
    
    locations = abg.abg_SUM( 
        MockData.pieceFactory().createKing( Color.WHITE ), 
        MockData.centerLocation(), 
        new Location( 7, 6, 7 ),
        2 );
    assertTrue( locations.size() == 16 );
    assertTrue( locations.contains( MockData.centerLocation() ));
    assertTrue( locations.contains( new Location( 7, 6, 7 ) ));
    assertTrue( locations.contains( new Location( 6, 6, 7 ) ));
    assertTrue( locations.contains( new Location( 8, 6, 7 ) ));
    assertTrue( locations.contains( new Location( 6, 7, 7 ) ));
    assertTrue( locations.contains( new Location( 8, 7, 7 ) ));
    
    
  }
  
  @Test
  public void testAbg_SUM_case2(){
    abg = MockData.flatAbstractBoardGame();
    Set<Location> locations = abg.abg_SUM( 
        MockData.pieceFactory().createPawn( Color.WHITE ), 
        MockData.flatCenterLocation(),
        new Location( 7, 5, 0 ),
        2 );
    assertTrue( locations.size() == 3 );
    assertTrue( locations.contains( new Location( 7, 6, 0 ) ));
  }
  
  @Test
  public void testAbg_SUM_case3(){
    abg = MockData.flatAbstractBoardGame();
    Set<Location> locations = abg.abg_SUM( 
        MockData.pieceFactory().createPawn( Color.WHITE ), 
        MockData.flatCenterLocation(),
        new Location( 7, 0, 0 ),
        7 );
    assertTrue( locations.size() == 8 );
    for( int i = 0; i < 8; ++ i ){
      assertTrue( locations.contains( new Location( 7, i, 0 ) ));
    }
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_MOVE(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location, lg.data_objects.location.Location, java.lang.Integer, java.lang.Integer)}.
   */
  @Test
  public void testAbg_MOVE() {
    abg = MockData.flatAbstractBoardGame();
    Set<Location> locations = 
        abg.abg_MOVE( MockData.pieceFactory().createKing( Color.WHITE ), 
                      MockData.flatCenterLocation(), 
                      new Location( 7, 5, 0 ), 
                      MockData.flatCenterLocation(), 
                      2, 
                      2 );
    assertTrue( locations.size() == 3 );
    assertTrue( locations.contains( new Location( 7, 6, 0 ) ));
    assertTrue( locations.contains( new Location( 6, 6, 0 ) ));
    assertTrue( locations.contains( new Location( 8, 6, 0 ) ));
    
    locations = 
        abg.abg_MOVE( MockData.pieceFactory().createKing( Color.WHITE ), 
                      MockData.flatCenterLocation(), 
                      new Location( 7, 5, 0 ), 
                      new Location( 7, 6, 0 ), 
                      2, 
                      1 );
    assertTrue( locations.size() == 1 );
    assertTrue( locations.contains( new Location( 7, 5, 0 ) ));
    
    abg.addPiece( MockData.pieceFactory().createObstacle(), new Location( 7, 6, 0) );
    
    locations = 
        abg.abg_MOVE( MockData.pieceFactory().createKing( Color.WHITE ), 
                      MockData.flatCenterLocation(), 
                      new Location( 7, 5, 0 ), 
                      MockData.flatCenterLocation(), 
                      2, 
                      2 );
    assertTrue( locations.size() == 2 );
    assertFalse( locations.contains( new Location( 7, 6, 0 ) ));
    assertTrue( locations.contains( new Location( 6, 6, 0 ) ));
    assertTrue( locations.contains( new Location( 8, 6, 0 ) ));
    
    abg.clearPieces();
    
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_DOCK(lg.data_objects.piece.Piece, lg.data_objects.location.Location, lg.data_objects.location.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_DOCK() {
    // same as SUM 
    // TODO remove
  }

}
