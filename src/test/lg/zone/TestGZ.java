package test.lg.zone;

import static org.junit.Assert.*;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.piece.Piece;
import lg.grammar.GZ;

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
    GZ gz = new GZ( abg, p0, q );
  }

}
