package chess;

import java.lang.Math;

import lg.abstract_board_game.AbstractBoard;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.reachability.ReachabilityRules;
import lg.reachability.ReachabilityRule;

/**
 * Simple non-abstract class instantiation factory for creating LG chess pieces
 * based on reachability rules.
 */
public class ChessPieceFactory extends PieceFactory {
  
  @Override
  public Piece createPiece( String name, Color color ) {
    if( name.toLowerCase().equals("pawn") ){
      return createPawn( color );
    } else if( name.toLowerCase().equals("rook") ){
      return createRook( color );
    } else if( name.toLowerCase().equals("bishop") ){
      return createBishop( color );
    } else if( name.toLowerCase().equals("knight") ){
      return createKnight( color );
    } else if( name.toLowerCase().equals("queen") ){
      return createQueen( color );
    } else if( name.toLowerCase().equals("king") ){
      return createKing( color );
    } else {
      return null;
    }
  }
  
  public Piece createPawn( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    // forward movement
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        int direction_value = ( color == Color.BLACK ) ? 1 : -1;
        return( x.getY() + direction_value == y.getY() &&
                x.getX() == y.getX() &&
                x.getZ() == y.getZ() ||
                x.equals(y) );
      }
    });
    
    Piece piece = new Piece( ChessConstants.NAME_PAWN, color, ChessConstants.VALUE_PAWN, reach);
    return piece;
  }
  
  public Piece createKnight( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( ( Math.abs(y.getX() - x.getX()) == 1 && 
                  Math.abs(y.getY() - x.getY()) == 2 &&
                  Math.abs(y.getZ() - x.getZ()) == 0 ) ||
                  
                ( Math.abs(y.getX() - x.getX()) == 2 && 
                  Math.abs(y.getY() - x.getY()) == 1 &&
                  Math.abs(y.getZ() - x.getZ()) == 0 ) ||
                  
                ( Math.abs(y.getX() - x.getX()) == 1 && 
                  Math.abs(y.getY() - x.getY()) == 0 &&
                  Math.abs(y.getZ() - x.getZ()) == 2 ) ||
                  
                ( Math.abs(y.getX() - x.getX()) == 2 && 
                  Math.abs(y.getY() - x.getY()) == 0 &&
                  Math.abs(y.getZ() - x.getZ()) == 1 ) ||
                  
                ( Math.abs(y.getX() - x.getX()) == 0 && 
                  Math.abs(y.getY() - x.getY()) == 2 &&
                  Math.abs(y.getZ() - x.getZ()) == 1 ) ||
                  
                ( Math.abs(y.getX() - x.getX()) == 0 && 
                  Math.abs(y.getY() - x.getY()) == 1 &&
                  Math.abs(y.getZ() - x.getZ()) == 2 ) ||
                  x.equals(y) );
               
      }
    });
    
    Piece piece = new Piece( ChessConstants.NAME_KNIGHT, color, ChessConstants.VALUE_KNIGHT, reach);
    return piece;
  }
  
  public Piece createBishop( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( ( Math.abs(x.getX() - y.getX()) == Math.abs(x.getY() - y.getY()) && x.getZ() == y.getZ() ) ||
                ( Math.abs(x.getZ() - y.getZ()) == Math.abs(x.getY() - y.getY()) && x.getX() == y.getX() ) ||
                ( Math.abs(x.getX() - y.getX()) == Math.abs(x.getZ() - y.getZ()) && x.getY() == y.getY() ) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_BISHOP, color, ChessConstants.VALUE_BISHOP, reach);
    return piece;
  }
  
  public Piece createRook( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( (x.getX() == y.getX() && x.getY() == y.getY()) ||
                (x.getZ() == y.getZ() && x.getY() == y.getY()) ||
                (x.getX() == y.getX() && x.getZ() == y.getZ()) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_ROOK, color, ChessConstants.VALUE_ROOK, reach);
    return piece;
  }
  
  public Piece createQueen( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( // 1D
                (x.getX() == y.getX() && x.getY() == y.getY()) ||
                (x.getZ() == y.getZ() && x.getY() == y.getY()) ||
                (x.getX() == y.getX() && x.getZ() == y.getZ()) ||
                // 2D
                ( Math.abs(x.getX() - y.getX()) == Math.abs(x.getY() - y.getY()) && x.getZ() == y.getZ() ) ||
                ( Math.abs(x.getZ() - y.getZ()) == Math.abs(x.getY() - y.getY()) && x.getX() == y.getX() ) ||
                ( Math.abs(x.getX() - y.getX()) == Math.abs(x.getZ() - y.getZ()) && x.getY() == y.getY() ) ||
                // 3D
                ( Math.abs(x.getX() - y.getX()) == Math.abs(x.getY() - y.getY()) &&
                  Math.abs(x.getX() - y.getX()) == Math.abs(x.getZ() - y.getZ()) &&
                  Math.abs(x.getY() - y.getY()) == Math.abs(x.getZ() - y.getZ()) ) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_QUEEN, color, ChessConstants.VALUE_QUEEN, reach);
    return piece;
  }
  
  public Piece createKing( final Color color ){
    ReachabilityRules reach = new ReachabilityRules();
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( Math.abs(x.getX() - y.getX()) <= 1 && Math.abs(x.getY() - y.getY()) <= 1 && Math.abs(x.getZ() - y.getZ()) <= 1 );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_KING, color, ChessConstants.VALUE_KING, reach);
    return piece;
  }

}
