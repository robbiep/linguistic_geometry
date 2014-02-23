package test;

import chess.ChessPieceFactory;
import lg.AbstractBoard;
import lg.AbstractBoardGame;
import lg.data_objects.Location;

/**
 * Creates singleton objects for reuse in testing
 */
public class MockData {
  
  public final static Integer DIMENSION = 15;
  public final static Integer CENTER    = 7;
  
  private static AbstractBoard      abstractBoard     = null;
  private static AbstractBoardGame  abstractBoardGame = null;
  private static ChessPieceFactory  chessPieceFactory = null;
  private static Location           centerLocation    = null;
  
  public static AbstractBoard abstractBoard(){
    if( abstractBoard == null ){
      abstractBoard = new AbstractBoard( DIMENSION, DIMENSION, DIMENSION );
    }
    return abstractBoard;
  }
  public static AbstractBoardGame abstractBoardGame(){
    if( abstractBoardGame == null ){
      abstractBoardGame = new AbstractBoardGame( abstractBoard() );
    }
    return abstractBoardGame;
  }
  
  public static ChessPieceFactory chessPieceFactory(){
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
}
