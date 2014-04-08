package test.chess;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoard;
import lg.data_objects.Color;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;

import org.junit.Before;
import org.junit.Test;

import test.MockData;
import chess.ChessConstants;
import chess.ChessPieceFactory;

public class TestPieceFactory {
  
  public ChessPieceFactory pieceFactory;
  
  @Before
  public void initialize(){
    pieceFactory = MockData.pieceFactory();
  }
  
  @Test
  public void testCreatePawn(){
    Piece piece = pieceFactory.createPawn( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_PAWN,   piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_PAWN,   piece.getValue() );
  }
  
  @Test
  public void testCreateKnight(){
    Piece piece = pieceFactory.createKnight( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_KNIGHT, piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_KNIGHT, piece.getValue() );
  }
  
  @Test
  public void testCreateBishop(){
    Piece piece = pieceFactory.createBishop( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_BISHOP, piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_BISHOP, piece.getValue() );
  }
  
  @Test
  public void testCreateRook(){
    Piece piece = pieceFactory.createRook( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_ROOK, piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_ROOK, piece.getValue() );
  }
  
  @Test
  public void testCreateQueen(){
    Piece piece = pieceFactory.createQueen( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_QUEEN, piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_QUEEN, piece.getValue() );
  }
  
  @Test
  public void testCreateKing(){
    Piece piece = pieceFactory.createKing( Color.BLACK );
    assertEquals( "Set/Get 'name'",   ChessConstants.NAME_KING, piece.getName() );
    assertEquals( "Set/Get 'group'",  Color.BLACK,  piece.getColor() );
    assertEquals( "Set/Get 'value'",  ChessConstants.VALUE_KING, piece.getValue() );
  }

}
