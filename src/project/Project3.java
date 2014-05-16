package project;

import java.util.Scanner;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_objects.zone.ZoneBundle;
import lg.data_objects.zone.ZoneTarget;
import lg.grammar.G_Z;
import chess.ChessPieceFactory;

public class Project3 {
  public static void run(){
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println( 
        "\nLG Project 3\n" +
        "------------\n" +
        "Generates zones for two different ABG configurations.\n");
    
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Piece q2 = factory.createBishop( Color.WHITE );
    Piece q1 = factory.createKing( Color.WHITE );
    Piece q0 = factory.createPawn( Color.BLACK );
    Piece p0 = PieceFactory.createTarget( Color.WHITE );
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
    G_Z gz = new G_Z( abg, q0, p0 );
    ZoneBundle zones = gz.executeGrammar();
   // ZoneBundle zones = gz.S( new ZoneTarget( location_q0, location_p0, 5 ) );
    System.out.println( "Configuration 1 - Correlates to the board found in lecture notes 8.");
    System.out.println( abg.toString() );
    System.out.println( zones.toString() );
    
    System.out.println("Press enter to generate configuration 2 found in lecture notes 7." );
    keyboard.nextLine();
    
    abg.clearPieces();
    Piece a0 = factory.createBishop( Color.WHITE );
    Piece a1 = factory.createKing( Color.WHITE );
    Piece b0 = PieceFactory.createTarget( Color.BLACK );
    Piece b1 = factory.createKing( Color.BLACK );
    Location location_a0 = new Location( 5, 6, 0 );
    Location location_a1 = new Location( 1, 7, 0 );
    Location location_b0 = new Location( 4, 3, 0 );
    Location location_b1 = new Location( 1, 1, 0 );
    abg.addPiece( a0, location_a0 );
    abg.addPiece( a1, location_a1 );
    abg.addPiece( b0, location_b0 );
    abg.addPiece( b1, location_b1 );
    gz = new G_Z( abg, a0, b0 );
    zones = gz.executeGrammar();
    System.out.println( abg.toString() );
    System.out.println( zones.toString() );
    
    
  //  System.out.println( "\nReti End Game Gateway = 2" );
 //   abg = ABG_Factory.getRetiBoard();
 //   gz = new G_Z( abg, factory.createPawn( Color.BLACK ), factory.createPawn( Color.WHITE ) );
  //  zones = gz.S( new ZoneTarget( new Location( 7, 3, 0 ), new Location( 7, 7, 0 ), 6 ) );
    
  }
}
