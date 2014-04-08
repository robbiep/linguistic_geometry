package project;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.zone.ZoneTarget;
import lg.grammar.GZ;
import chess.ChessPieceFactory;

public class Project3 {
  public static void run(){
    System.out.println( 
        "\nLG Project 3\n" +
        "------------\n" +
        "Generates zones for two different ABG configurations.\n");
    
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Piece p0 = factory.createBishop( Color.WHITE );
    Piece q  = factory.createPawn( Color.BLACK );
    GZ gz = new GZ( abg, p0, q );
    gz.S( new ZoneTarget( new Location( 0, 0, 0 ), new Location( 0, 2, 0), 2 ) );
  }
}
