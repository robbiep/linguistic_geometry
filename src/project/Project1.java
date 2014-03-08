package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import chess.ChessPieceFactory;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.reachability.ReachabilityTable;


public class Project1 {

  public static void run(){
    System.out.println( 
        "\nLG Project 1\n" +
        "------------\n" +
        "Prints a 15x15x1 reachability table for each chess piece.\n" +
        "The starting location for each piece is set in the center.\n");
 
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 15, 15, 1, factory );
    Location center = new Location( 7, 7, 0 );
    ArrayList<ReachabilityTable> tables = new ArrayList<ReachabilityTable>();
    //Scanner enter_to_continue = new Scanner(System.in);
    tables.add( abg.getReachabilityTable( 
        factory.createPawn( Color.WHITE ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createPawn( Color.BLACK ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createRook( Color.WHITE ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createKnight( Color.WHITE ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createBishop( Color.WHITE ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createQueen( Color.WHITE ), center ));
    tables.add( abg.getReachabilityTable( 
        factory.createKing( Color.WHITE ), center ));
    
    Iterator<ReachabilityTable> it = tables.iterator();
    while( it.hasNext() ){
      ReachabilityTable table = it.next();
      System.out.println( "Piece: " + table.getPiece().getName() + 
          " (" + table.getPiece().getColor().toString()+ ")" );
      table.printReachabilityTable(0);
     // System.out.println("Press enter to continue...");
      //enter_to_continue.nextLine();
    }
  }
}

