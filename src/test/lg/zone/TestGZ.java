package test.lg.zone;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_objects.zone.ZoneBundle;
import lg.grammar.G_Z;

import org.junit.Test;

import chess.ChessPieceFactory;
import test.MockData;

public class TestGZ {

  @Test
  public void constructGZ(){
    ChessPieceFactory factory = MockData.pieceFactory();
    AbstractBoardGame abg = MockData.emptyChestGame();
    Piece p0 = factory.createBishop( Color.WHITE );
    Piece q  = factory.createPawn( Color.BLACK );
    Location x = new Location();
    Location y = new Location();
    G_Z gz = new G_Z( abg, p0, q );
  }
  
  @Test
  public void testPreTransition1(){
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
    G_Z gz = new G_Z( abg, q0, p0 );
    ZoneBundle zones = gz.executeGrammar();
    System.out.println( zones.toString() );
  }
  
  @Test
  public void testPostTransition1(){
    ChessPieceFactory factory = new ChessPieceFactory();
    AbstractBoardGame abg = new AbstractBoardGame( 8, 8, 1, factory );
    Piece q2 = factory.createBishop( Color.WHITE );
    Piece q1 = factory.createKing( Color.WHITE );
    Piece q0 = factory.createPawn( Color.BLACK );
    Piece p0 = factory.createPawn( Color.WHITE );
    Location location_q2 = new Location( 3, 1, 0 );
    Location location_q1 = new Location( 6, 3, 0 );
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
    System.out.println( zones.toString() );
  }

}
