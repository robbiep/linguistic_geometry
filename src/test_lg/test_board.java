package test_lg;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lg.AbstractBoard;
import lg.Location;

import org.junit.Test;

public class test_board {

  @Test
  public void testBoard() {
    AbstractBoard board;
    
    board = new AbstractBoard(2);
    assertEquals(new Integer(2), board.getX());
    assertEquals(new Integer(1), board.getY());
    assertEquals(new Integer(1), board.getZ());
    
    board = new AbstractBoard(2,2);
    assertEquals(new Integer(2), board.getX());
    assertEquals(new Integer(2), board.getY());
    assertEquals(new Integer(1), board.getZ());
    
    board = new AbstractBoard(2,2,2);
    assertEquals(new Integer(2), board.getX());
    assertEquals(new Integer(2), board.getY());
    assertEquals(new Integer(2), board.getZ());
    
    board = new AbstractBoard(2,2,2, new ArrayList<Location>());
    assertEquals(new Integer(2), board.getX());
    assertEquals(new Integer(2), board.getY());
    assertEquals(new Integer(2), board.getZ());

  }
  
  @Test
  public void testBoardValidLocation(){
    AbstractBoard board;
    
    board = new AbstractBoard(2,2,2, new ArrayList<Location>());
    assertTrue(board.validLocation(new Location(1,1,1)));
    
    ArrayList<Location> obstacles = new ArrayList<Location>();
    obstacles.add(new Location(1,2,1));
    
    board = new AbstractBoard(2,2,2, obstacles);
    assertTrue(board.validLocation(new Location(1,1,1)));
    assertFalse(board.validLocation(new Location(1,2,1)));
  }

}
