package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.Trajectory;
import lg.data_structures.GamePiece;
import lg.grammar.GT2;
import chess.ChessPieceFactory;

public class Project2 {
  
  public static class Tuple{
    public Piece piece;
    public String trajectory;
    public int length;
    public Tuple( Piece piece, String trajectory, int length ) {
      this.piece = piece;
      this.trajectory = trajectory;
      this.length = length;
    }
  }
  
  public static void run(){
    System.out.println( 
        "\nLG Project 2\n" +
        "------------\n" +
        "Outputs a trajectory from position 5,5,0 to 5,2,0 of length 1 and 2\n" );
    
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    GT2 gt2 = new GT2( abg );
    Location location_x = new Location( 5, 5, 0 );
    Location location_y = new Location( 5, 2, 0 );
    
    ArrayList<Tuple> trajectories = new ArrayList<Tuple>();
    
    //Scanner enter_to_continue = new Scanner(System.in);
    Piece piece = factory.createPawn( Color.WHITE );
    Integer length = 3;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));
    length = 4;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));
    
    piece = factory.createRook( Color.BLACK );
    length = 1;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));
    length = 2;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    
    piece = factory.createBishop( Color.BLACK );
    length = 2;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    length = 3;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    
    piece = factory.createKnight( Color.BLACK );
    length = 3;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    length = 4;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    
    piece = factory.createQueen( Color.BLACK );
    length = 1;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    length = 2;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    
    piece = factory.createKing( Color.BLACK );
    length = 3;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(),
            length));
    length = 4;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));

    Iterator<Tuple> it = trajectories.iterator();
    while( it.hasNext() ){
      Tuple next = it.next();
      System.out.println( "Piece: " + next.piece.getName() + 
          " (" + next.piece.getColor().toString()+ ")" + " Length: " + next.length );
      System.out.println( next.trajectory + "\n" );
      //System.out.println("Press enter to continue...");
      //enter_to_continue.nextLine();
    }
  }
}
