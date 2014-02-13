package chess;

/**
 * Basic constants that define the game of chess.
 */
public interface ChessConstants {
  public static final String  COLOR_WHITE   = "White";
  public static final String  COLOR_BLACK   = "Black";
  
  public static final String  NAME_PAWN     = "Pawn";
  public static final String  NAME_KNIGHT   = "Knight";
  public static final String  NAME_ROOK     = "Rook";
  public static final String  NAME_BISHOP   = "Bishop";
  public static final String  NAME_QUEEN    = "Queen";
  public static final String  NAME_KING     = "King";
  
  public static final Integer VALUE_PAWN    = 1;
  public static final Integer VALUE_KNIGHT  = 3;
  public static final Integer VALUE_BISHOP  = 3;
  public static final Integer VALUE_ROOK    = 5;
  public static final Integer VALUE_QUEEN   = 9;
  public static final Integer VALUE_KING    = Integer.MAX_VALUE;
}
