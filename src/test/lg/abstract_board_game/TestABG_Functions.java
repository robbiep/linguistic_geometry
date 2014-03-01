/**
 * 
 */
package test.lg.abstract_board_game;

import static org.junit.Assert.*;
import lg.abstract_board_game.ABG_Functions;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;

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
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_R(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location)}.
   */
  @Test
  public void testAbg_R() {
    abg.addPiece( MockData.chessPieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
    assertTrue( abg.abg_R(  MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                            MockData.centerLocation(), 
                            new Location( 7, 6, 7 ) ));
    assertFalse( abg.abg_R( MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                            MockData.centerLocation(), 
                            new Location( 7, 8, 7 ) ));
    assertFalse( abg.abg_R( MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                            new Location( 7, 8, 7 ),
                            MockData.centerLocation() ));
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_ON(lg.data_objects.Piece)}.
   */
  @Test
  public void testAbg_ON() {
    abg.addPiece( MockData.chessPieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
    assertTrue( abg.abg_ON( MockData.centerLocation() ).equals(MockData.chessPieceFactory().createPawn( Color.WHITE )));
    assertTrue( abg.abg_ON( new Location( 1, 1, 1 ) ) == null );
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_TR(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location)}.
   */
  @Test
  public void testAbg_TR() {
    abg.addPiece( MockData.chessPieceFactory().createPawn( Color.WHITE ), MockData.centerLocation() );
    Location location2 = new Location( 7, 6, 7 );
    
    // Move piece to valid empty location
    assertTrue(
        abg.abg_TR( MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                    MockData.centerLocation(), 
                    location2 )
        );
    assertTrue( abg.abg_ON( location2 ).equals( 
        MockData.chessPieceFactory().createPawn( Color.WHITE ) ));
    
    // Move piece to non-reachable empty location
    assertFalse(
        abg.abg_TR( MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                    location2, 
                    MockData.centerLocation() )
        );
    assertTrue( abg.abg_ON( location2 ).equals( 
        MockData.chessPieceFactory().createPawn( Color.WHITE ) ));
    
    // No piece to move
    assertFalse(
        abg.abg_TR( MockData.chessPieceFactory().createPawn( Color.WHITE ), 
                    MockData.centerLocation(), 
                    location2 )
        );
    assertTrue( abg.abg_ON( location2 ).equals( 
        MockData.chessPieceFactory().createPawn( Color.WHITE ) ));
    
 // Move piece to enemy location
    abg.addPiece( MockData.chessPieceFactory().createQueen( Color.BLACK ), MockData.centerLocation() );
    assertTrue(
        abg.abg_TR( MockData.chessPieceFactory().createQueen( Color.BLACK ), 
                    MockData.centerLocation(), 
                    location2 )
        );
    assertTrue( abg.abg_ON( location2 ).equals( 
        MockData.chessPieceFactory().createQueen( Color.BLACK ) ));
    
 // Move piece to friendly location
    abg.addPiece( MockData.chessPieceFactory().createQueen( Color.BLACK ), MockData.centerLocation() );
    assertFalse(
        abg.abg_TR( MockData.chessPieceFactory().createQueen( Color.BLACK ), 
                    MockData.centerLocation(), 
                    location2 )
        );
    assertTrue( abg.abg_ON( location2 ).equals( 
        MockData.chessPieceFactory().createQueen( Color.BLACK ) ));
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_MAP(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location)}.
   */
  @Test
  public void testAbg_MAP() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_ST(lg.data_objects.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_ST() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_SUM(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_SUM() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_MOVE(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location, lg.data_objects.Location, java.lang.Integer, java.lang.Integer)}.
   */
  @Test
  public void testAbg_MOVE() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link lg.abstract_board_game.ABG_Functions#abg_DOCK(lg.data_objects.Piece, lg.data_objects.Location, lg.data_objects.Location, java.lang.Integer)}.
   */
  @Test
  public void testAbg_DOCK() {
    fail("Not yet implemented");
  }

}
