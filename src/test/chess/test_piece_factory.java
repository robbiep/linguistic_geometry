package test.chess;

import static org.junit.Assert.*;
import lg.AbstractBoard;
import lg.data_objects.Piece;

import org.junit.Before;
import org.junit.Test;

import chess.ChessConstants;
import chess.ChessPieceFactory;

public class test_piece_factory {
  
  public AbstractBoard ab;
  public ChessPieceFactory factory;
  
  @Before
  public void initialize(){
    ab = new AbstractBoard( 10, 10, 10 );
    factory = new ChessPieceFactory( ab );
 }

  @Test
  public void testCreatePawn(){
    Piece piece = factory.createPawn( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_PAWN,   piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_PAWN,   piece.getValue() );
  }
  
  @Test
  public void testCreateKnight(){
    Piece piece = factory.createKnight( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_KNIGHT, piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_KNIGHT, piece.getValue() );
  }
  
  @Test
  public void testCreateBishop(){
    Piece piece = factory.createBishop( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_BISHOP, piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_BISHOP, piece.getValue() );
  }
  
  @Test
  public void testCreateRook(){
    Piece piece = factory.createRook( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_ROOK, piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_ROOK, piece.getValue() );
  }
  
  @Test
  public void testCreateQueen(){
    Piece piece = factory.createQueen( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_QUEEN, piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_QUEEN, piece.getValue() );
  }
  
  @Test
  public void testCreateKing(){
    Piece piece = factory.createKing( ChessConstants.COLOR_BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_KING, piece.getName() );
    assertEquals( "Set/Get 'group'",  ChessConstants.COLOR_BLACK,  piece.getGroup() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_KING, piece.getValue() );
  }

}
