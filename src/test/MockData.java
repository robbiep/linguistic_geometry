package test;

import chess.ChessPieceFactory;
import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;

/**
 * Creates singleton objects for reuse in testing
 */
public class MockData {
  
  public final static Integer DIMENSION       = 15;
  public final static Integer CENTER          = 7;
  public final static Integer CHESS_DIMENSION = 8;
  
  private static AbstractBoard      abstractBoard         = null;
  private static AbstractBoardGame  abstractBoardGame     = null;
  private static AbstractBoardGame  flatAbstractBoardGame = null;
  private static ChessPieceFactory  chessPieceFactory     = null;
  private static Location           centerLocation        = null;
 
  
  public static AbstractBoard abstractBoard(){
    if( abstractBoard == null ){
      abstractBoard = new AbstractBoard( DIMENSION, DIMENSION, DIMENSION );
    }
    return abstractBoard;
  }
  
  public static AbstractBoard abstractBoardChess(){
    if( abstractBoard == null ){
      abstractBoard = new AbstractBoard(  CHESS_DIMENSION, 
                                          CHESS_DIMENSION, 
                                          1 );
    }
    return abstractBoard;
  }
  
  
  
  public static ChessPieceFactory pieceFactory(){
    if( chessPieceFactory == null ){
      chessPieceFactory = new ChessPieceFactory( abstractBoard() );
    }
    return chessPieceFactory;
  }
  
  public static Location centerLocation(){
    if( centerLocation == null ){
      centerLocation = new Location( CENTER, CENTER, CENTER );
    }
    return centerLocation;
  }
  
  public static AbstractBoardGame abstractBoardGame(){
    if( abstractBoardGame == null ){
      abstractBoardGame = new AbstractBoardGame( abstractBoard() );
    }
    return abstractBoardGame;
  }
  
  public static AbstractBoardGame flatAbstractBoardGame(){
    if( flatAbstractBoardGame  == null ){
      flatAbstractBoardGame = new AbstractBoardGame( 
          new AbstractBoard( DIMENSION, DIMENSION, 1 ) );
    }
    return flatAbstractBoardGame;
  }
  
  public static AbstractBoardGame chessGame(){
    AbstractBoardGame abg = new AbstractBoardGame( abstractBoardChess() );
    // Pawns
    for( int i = 0; i < CHESS_DIMENSION; ++ i ){
      abg.addPiece( pieceFactory().createPawn( Color.WHITE ),
                    new Location( i,1,0 ) );
      abg.addPiece( pieceFactory().createPawn( Color.BLACK ),
                    new Location( i,6,0 ) );
    }
    // Rooks
    abg.addPiece( pieceFactory().createRook( Color.WHITE ),
                  new Location( 0,0,0 ) );
    abg.addPiece( pieceFactory().createRook( Color.WHITE ),
                  new Location( 7,0,0 ) );
    abg.addPiece( pieceFactory().createRook( Color.BLACK ),
                  new Location( 0,7,0 ) );
    abg.addPiece( pieceFactory().createRook( Color.BLACK ),
                  new Location( 7,7,0 ) );
    // Knights
    abg.addPiece( pieceFactory().createKnight( Color.WHITE ),
                  new Location( 1,0,0 ) );
    abg.addPiece( pieceFactory().createKnight( Color.WHITE ),
                  new Location( 6,0,0 ) );
    abg.addPiece( pieceFactory().createKnight( Color.BLACK ),
                  new Location( 1,7,0 ) );
    abg.addPiece( pieceFactory().createKnight( Color.BLACK ),
                  new Location( 6,7,0 ) );
    // Bishops
    abg.addPiece( pieceFactory().createBishop( Color.WHITE ),
                  new Location( 2,0,0 ) );
    abg.addPiece( pieceFactory().createBishop( Color.WHITE ),
                  new Location( 5,0,0 ) );
    abg.addPiece( pieceFactory().createBishop( Color.BLACK ),
                  new Location( 2,7,0 ) );
    abg.addPiece( pieceFactory().createBishop( Color.BLACK ),
                  new Location( 5,7,0 ) );
    // Queens
    abg.addPiece( pieceFactory().createQueen( Color.WHITE ),
                  new Location( 3,0,0 ) );
    abg.addPiece( pieceFactory().createQueen( Color.BLACK ),
                  new Location( 3,7,0 ) );
    // kings
    abg.addPiece( pieceFactory().createQueen( Color.WHITE ),
                  new Location( 4,0,0 ) );
    abg.addPiece( pieceFactory().createQueen( Color.BLACK ),
                  new Location( 4,7,0 ) );
    return abg;
  }
}
