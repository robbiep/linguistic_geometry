package test.lg.abstract_board_game;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_structures.GamePiece;

import org.junit.Before;
import org.junit.Test;

import chess.ChessConstants;
import test.MockData;

public class TestAbstractBoardGame {

  AbstractBoardGame abg;
  
  @Before
  public void initialize(){
    abg = MockData.chessGame();
  }
  
  @Test
  public void testAbstractBoardGameAbstractBoard(){
    abg = new AbstractBoardGame( MockData.abstractBoard(), MockData.pieceFactory() );
  }

  @Test
  public void testAbstractBoardGameIntegerIntegerInteger(){
    abg = new AbstractBoardGame(  MockData.CHESS_DIMENSION, 
                                  MockData.CHESS_DIMENSION, 
                                  MockData.CHESS_DIMENSION,
                                  MockData.pieceFactory());
  }

  @Test
  public void testGetAbstractBoard(){
    assertTrue( abg.getAbstractBoard().equals( MockData.abstractBoard() ));
  }

  @Test
  public void testGetDimX(){
    assertTrue( abg.getDimX() == MockData.CHESS_DIMENSION );
  }

  @Test
  public void testGetDimY(){
    assertTrue( abg.getDimY() == MockData.CHESS_DIMENSION );
  }

  @Test
  public void testGetDimZ(){
    assertTrue( abg.getDimZ() == MockData.CHESS_DIMENSION );
  }

  @Test
  public void testSetAbstractBoard(){
    abg.setAbstractBoard( new AbstractBoard(  
        MockData.CHESS_DIMENSION, 
        MockData.CHESS_DIMENSION - 1, 
        1 ));
    assertFalse( abg.getAbstractBoard().equals( MockData.abstractBoard() ));
    assertTrue( abg.getByLocation( new Location( 7, 7, 0 ) ) == null );
    assertTrue( abg.getByLocation( new Location( 6, 6, 0 ) ) != null );
  }

  @Test
  public void testGetByPiece(){
    // TODO
  }

  @Test
  public void testGetByLocation(){
    Piece piece = abg.getByLocation( new Location( 7, 7, 0 ) );
    assertTrue( piece.getName().equals( ChessConstants.NAME_ROOK ));
    assertTrue( piece.getColor().equals( Color.BLACK ));
  }

  @Test
  public void testGetAllByColor(){
    GamePiece[] game_piece = abg.getAllPiecesByColor( Color.WHITE );
    assertTrue( game_piece.length == 16 );
  }

  @Test
  public void testClearAllByColor(){
    abg.clearAllByColor( Color.WHITE );
    assertTrue( abg.getAllPiecesByColor( Color.WHITE ).length == 0 );
  }

  @Test
  public void testAddPiece(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testRemovePiece(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testClearPieces(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testValidLocation(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testGetReachabilityTable(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testAbg_R(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testAbg_ON(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testAbg_TR(){
    fail( "Not yet implemented" ); // TODO
  }

  @Test
  public void testAbg_MAP(){
    fail( "Not yet implemented" ); // TODO
  }

}
