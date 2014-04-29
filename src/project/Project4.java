package project;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_objects.translations.Translations;
import lg.grammar.G_RS;
import chess.ChessPieceFactory;

public class Project4 {
  public static void run(){
    System.out.println( 
        "\nLG Project 4\n" +
        "------------\n" +
        "Implementation of grammar of reduced searches.\n");
    
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Piece q2 = factory.createBishop( Color.WHITE );
    Piece q1 = factory.createKing( Color.WHITE );
    Piece q0 = factory.createPawn( Color.BLACK );
    Piece p0 = factory.createPawn( Color.WHITE );
    Location location_q2 = new Location( 3, 1, 0 );
    Location location_q1 = new Location( 5, 2, 0 );
    Location location_q0 = new Location( 7, 3, 0 );
    Location location_p0 = new Location( 7, 7, 0 );
    abg.addPiece( q2, location_q2 );
    abg.addPiece( q1, location_q1 );
    abg.addPiece( q0, location_q0 );
    abg.addPiece( p0, location_p0 );
    abg.addPiece( PieceFactory.createObstacle(), new Location( 3, 3, 0 ) );
    abg.addPiece( PieceFactory.createObstacle(), new Location( 4, 2, 0 ) );
    abg.addPiece( PieceFactory.createObstacle(), new Location( 5, 1, 0 ) );
    abg.addPiece( PieceFactory.createObstacle(), new Location( 6, 5, 0 ) );
    abg.addPiece( PieceFactory.createObstacle(), new Location( 6, 4, 0 ) );
    
    G_RS g_rs = new G_RS( abg, q0, p0, location_q0, location_p0 );
    Translations translations = g_rs.executeGrammar();
    System.out.println( translations.toString() );
  }
}
