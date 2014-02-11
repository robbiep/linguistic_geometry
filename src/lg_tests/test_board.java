package lg_tests;

import static org.junit.Assert.*;
import lg.AbstractBoard;

import org.junit.Test;

public class test_board {

  @Test
  public void testBoard() {
    AbstractBoard board;
    board = new AbstractBoard(2);
    board = new AbstractBoard(2,2);
    board = new AbstractBoard(2,2,2);
  }

}
