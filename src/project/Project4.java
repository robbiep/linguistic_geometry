package project;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_objects.translations.Translations;
import lg.data_objects.zone.MV;
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
    Piece whiteKing = factory.createKing( Color.WHITE );
    Piece whitePawn = factory.createPawn( Color.WHITE );
    Piece whiteTarget = factory.createTarget( Color.WHITE );
    Piece blackKing = factory.createKing( Color.BLACK );
    Piece blackPawn = factory.createPawn( Color.BLACK );
    Piece blackTarget = factory.createTarget( Color.BLACK );
    Location location_whiteKing = new Location( 7, 0, 0 );
    Location location_whitePawn = new Location( 2, 2, 0 );
    Location location_whiteTarget = new Location( 7, 7, 0 );
    Location location_blackKing = new Location( 0, 2, 0 );
    Location location_blackPawn = new Location( 7, 2, 0 );
    Location location_blackTarget = new Location( 2, 0, 0 );
    abg.addPiece( whiteKing, location_whiteKing );
    abg.addPiece( whitePawn, location_whitePawn );
    abg.addPiece( whiteTarget, location_whiteTarget );
    abg.addPiece( blackKing, location_blackKing );
    abg.addPiece( blackPawn, location_blackPawn );
    abg.addPiece( blackTarget, location_blackTarget );
    
    G_RS g_rs = new G_RS( abg, blackPawn, whiteTarget, location_blackPawn, location_whiteTarget );
    g_rs.setMV( new MV( Project4_MV.retiTransitions() ) );
    Translations translations = g_rs.executeGrammar();
    System.out.println( translations.toString() );
  }
}
