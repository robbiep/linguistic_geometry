package test.lg.trajectory;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.data_objects.Piece;
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
    gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    gt2.printTrajectory();
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
    gt2.generateTrajectory( piece, 
                            location_x, 
                            location_y, 
                            length );
    gt2.printTrajectory();
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
    gt2.generateTrajectory( piece, 
        location_x, 
        location_y, 
        length );
    gt2.printTrajectory();
  }

}
