package test.lg.zone;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
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
    G_Z gz = new G_Z( abg, p0, q, x, y );
  }

}
