package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_objects.trajectory.Trajectory;
import lg.data_objects.trajectory.TrajectoryBundle;
import lg.data_structures.GamePiece;
import lg.grammar.G_T2;
import lg.reachability.ReachabilityRule;
import lg.reachability.ReachabilityRules;
import chess.ChessPieceFactory;
import chess.IrregularPieceFactory;

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
  static Scanner keyboard;
  static ChessPieceFactory factory = new ChessPieceFactory();
  static AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
  static G_T2 gt2 = new G_T2( abg );
  static Location location_x = new Location( 5, 5, 0 );
  static Location location_y = new Location( 5, 2, 0 );
  static ArrayList<Tuple> trajectories = new ArrayList<Tuple>();
  static Piece piece;
  static int length = 3;
  static Iterator<Tuple> it;
  
  public static void run(){
    keyboard = new Scanner(System.in);
    System.out.println( 
        "\nLG Project 2\n" +
        "------------\n" );
    
    genTrajectoriesNoObstacle();
    
    genTrajectoriesWithObstacle();
    
    genIrregularTrajecotry();
    
    kingA5H5();
  }

  private static void kingA5H5(){
    System.out.println("Press enter to generate all trajectories for a\n"
        + "KING from a5 to h5...");
    keyboard.nextLine();
    
    abg = new AbstractBoardGame( 8, 8, 1, factory );
    gt2 = new G_T2( abg );
    piece = factory.createKing( Color.WHITE );
    location_x = new Location( 0, 4, 0 );
    location_y = new Location( 7, 4, 0 );
    length = 7;
    TrajectoryBundle kingA5H5 = gt2.generateTrajectory( 
        piece, 
        location_x , 
        location_y, 
        length );
    System.out.println( "The locations (0, 4, 0) ... (7, 4, 0) correlates "
        + "to a trajectory from a5 to h5");
    System.out.println( "Piece: " + piece.getName() + 
        " (" + piece.getColor().toString()+ ")" + " Length: " + length );
    System.out.println( kingA5H5.toString() );
    System.out.println( "The grammar generated a total of: " +  kingA5H5.size() 
        + " trajectories for the KING.\n\n" );
    
  }

  private static void genTrajectoriesWithObstacle(){
    System.out.println("Press enter to output a trajectories from position\n"
        + "5,5,0 to 5,2,0 of admissible length 1 and 2 for a board with\n"
        + "an obstacle at (5,3,0)...");
    keyboard.nextLine();
    
    trajectories.clear();
    abg = new AbstractBoardGame( 8, 8, 1, factory );
    abg.addPiece( PieceFactory.createObstacle(), new Location(5,3,0) );
    gt2 = new G_T2( abg );
    
    piece = factory.createPawn( Color.WHITE );
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
    
    piece = factory.createKing( Color.WHITE );
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
    
    
    it = trajectories.iterator();
    while( it.hasNext() ){
      Tuple next = it.next();
      System.out.println( "Piece: " + next.piece.getName() + 
          " (" + next.piece.getColor().toString()+ ")" + 
          " Length: " + next.length );
      System.out.println( next.trajectory + "\n" );
    }
  }

  private static void genTrajectoriesNoObstacle(){
    System.out.println("Press enter to output trajectories from position\n"
        + "5,5,0 to 5,2,0 of admissible length 1 and 2 for a board "
        + "with no obstacles...");
    keyboard.nextLine();
    trajectories.clear();
    
    abg = new AbstractBoardGame( 8, 8, 1, factory );
    piece = factory.createPawn( Color.WHITE );
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
    
    piece = factory.createKing( Color.WHITE );
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
    
    
    it = trajectories.iterator();
    while( it.hasNext() ){
      Tuple next = it.next();
      System.out.println( "Piece: " + next.piece.getName() + 
          " (" + next.piece.getColor().toString()+ ")" + 
          " Length: " + next.length );
      System.out.println( next.trajectory + "\n" );
    }
  }
  
  private static void genIrregularTrajecotry(){
    IrregularPieceFactory irregularPieceFactory = new IrregularPieceFactory();
    abg = new AbstractBoardGame( 2, 8, 2, irregularPieceFactory );
    gt2 = new G_T2( abg );
    trajectories.clear();
    location_x = new Location( 0, 0, 0 );
    location_y = new Location( 0, 7, 0 );
    
    piece = irregularPieceFactory.createPiece( "Irregular", Color.BLACK );

    System.out.println("Press enter to output trajectories from (0, 0, 0) "
        + "to (0, 7, 0) of an\n"
        + "irregular piece on a 2 x 8 x 2 board. The irregular piece can only\n"
        + "move 'forward' (up in value on y-axis) by three units, \n"
        + "But can move, sideways and backward\n"
        + "by 1. No diagonal movement is allowed,..");
    keyboard.nextLine();
    
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
    length = 5;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));
    length = 6;
    trajectories.add( 
        new Tuple( piece, 
            gt2.generateTrajectory( 
                piece, 
                location_x , 
                location_y, 
                length ).toString(), 
            length));
    it = trajectories.iterator();
    while( it.hasNext() ){
      Tuple next = it.next();
      System.out.println( "Piece: " + next.piece.getName() + 
          " (" + next.piece.getColor().toString()+ ")" + 
          " Length: " + next.length );
      System.out.println( next.trajectory + "\n" );
    }
  }
}
