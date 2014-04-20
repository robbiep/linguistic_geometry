package test.lg.trajectory;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.TrajectoryBundle;
import lg.data_structures.GamePiece;
import lg.grammar.GT2;

import org.junit.Test;

import chess.ChessPieceFactory;
import project.Project2.Tuple;

public class TestGT2 {

  @Test
  public void testPawnTrajectory(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Location location_x = new Location( 5, 5, 0 );
    Location location_y = new Location( 5, 2, 0 );
    GT2 gt2 = new GT2( abg );
    Piece piece = factory.createPawn( Color.WHITE );
    Integer length = 3;
    TrajectoryBundle bundle = gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    System.out.print( bundle.toString() );
  }
  
  @Test
  public void testPawnInvalidTrajectory(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Location location_x = new Location( 5, 5, 0 );
    Location location_y = new Location( 5, 2, 0 );
    GT2 gt2 = new GT2( abg );
    Piece piece = factory.createPawn( Color.WHITE );
    Integer length = 4;
    TrajectoryBundle bundle = gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    System.out.print( bundle.toString() );
  }
  
  @Test
  public void testKingAdmissable1(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Location location_x = new Location( 5, 5, 0 );
    Location location_y = new Location( 5, 2, 0 );
    GT2 gt2 = new GT2( abg );
    Piece piece = factory.createKing( Color.WHITE );
    Integer length = 3;
    TrajectoryBundle bundle = gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    System.out.print( bundle.toString() );
  }
  @Test
  public void testKingAdmissable1b(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Location location_x = new Location( 0, 4, 0 );
    Location location_y = new Location( 7, 4, 0 );
    GT2 gt2 = new GT2( abg );
    Piece piece = factory.createKing( Color.WHITE );
    Integer length = 7;
    TrajectoryBundle bundle = gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    System.out.println( bundle.toString() );
    System.out.println( "Bundle size = " + bundle.size() );
  }
  
  @Test
  public void testKingAdmissable2(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Location location_x = new Location( 5, 5, 0 );
    Location location_y = new Location( 5, 2, 0 );
    GT2 gt2 = new GT2( abg );
    Piece piece = factory.createKing( Color.WHITE );
    Integer length = 4;
    TrajectoryBundle bundle = gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    System.out.print( bundle.toString() );
  }

}
