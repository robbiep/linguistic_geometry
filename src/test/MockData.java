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
  
  private static AbstractBoard      abstract_board            = null;
  private static AbstractBoardGame  abstract_board_game       = null;
  private static AbstractBoardGame  flat_abstract_board_game  = null;
  private static ChessPieceFactory  chess_piece_factory       = null;
  private static Location           center_location           = null;
  private static Location           flat_center_location      = null;
 
  
  public static AbstractBoard abstractBoard(){
    if( abstract_board == null ){
      abstract_board = new AbstractBoard( DIMENSION, DIMENSION, DIMENSION );
    }
    return abstract_board;
  }
  
  public static AbstractBoard abstractBoardChess(){
    if( abstract_board == null ){
      abstract_board = new AbstractBoard(  CHESS_DIMENSION, 
                                          CHESS_DIMENSION, 
                                          1 );
    }
    return abstract_board;
  }
  
  
  
  public static ChessPieceFactory pieceFactory(){
    if( chess_piece_factory == null ){
      chess_piece_factory = new ChessPieceFactory( abstractBoard() );
    }
    return chess_piece_factory;
  }
  
  public static Location centerLocation(){
    if( center_location == null ){
      center_location = new Location( CENTER, CENTER, CENTER );
    }
    return center_location;
  }
  
  public static Location flatCenterLocation(){
    if( flat_center_location == null ){
      flat_center_location = new Location( CENTER, CENTER, 0 );
    }
    return flat_center_location;
  }
  
  public static AbstractBoardGame abstractBoardGame(){
    if( abstract_board_game == null ){
      abstract_board_game = new AbstractBoardGame( abstractBoard() );
    }
    return abstract_board_game;
  }
  
  public static AbstractBoardGame flatAbstractBoardGame(){
    if( flat_abstract_board_game  == null ){
      flat_abstract_board_game = new AbstractBoardGame( 
          new AbstractBoard( DIMENSION, DIMENSION, 1 ) );
    }
    return flat_abstract_board_game;
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
