package project;

import chess.ChessPieceFactory;
import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;

public class ABG_Factory {
  public static AbstractBoardGame getChessBoard(){
    return new AbstractBoardGame( 
        new AbstractBoard( 8, 8, 1),
        new ChessPieceFactory() );
  }
  
  public static AbstractBoardGame getRetiBoard(){
    AbstractBoardGame abg = getChessBoard();
    ChessPieceFactory factory = new ChessPieceFactory();
    abg.addPiece( 
        factory.createPawn( Color.BLACK ), 
        new Location( 2, 0, 0 ) );
    abg.addPiece( 
        factory.createKing( Color.WHITE ), 
        new Location( 7, 0, 0 ) );
    abg.addPiece( 
        factory.createKing( Color.BLACK ), 
        new Location( 0, 2, 0 ) );
    abg.addPiece( 
        factory.createPawn( Color.WHITE ), 
        new Location( 2, 2, 0 ) );
    abg.addPiece( 
        factory.createPawn( Color.BLACK ), 
        new Location( 7, 3, 0 ) );
    abg.addPiece( 
        factory.createPawn( Color.WHITE ), 
        new Location( 7, 7, 0 ) );
    return abg;
  }
}
