package chess;

import java.lang.Math;

import lg.AbstractBoard;
import lg.Location;
import lg.Piece;
import lg.Reachability;
import lg.ReachabilityRule;

/**
 * Simple non-abstract class instantiation factory for creating LG chess pieces
 * based on reachability rules.
 */
public class ChessPieceFactory {
  
  private AbstractBoard ab;
  
  public ChessPieceFactory( AbstractBoard ab ){
    this.ab = ab;
  }
  
  public Piece createPawn( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    // forward movement
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        int direction_value = ( color == ChessConstants.COLOR_BLACK ) ? -1 : 1;
        return( x.y + direction_value == y.y &&
                x.x == y.x &&
                x.z == y.z ||
                x.equals(y) );
      }
    });
    
    Piece piece = new Piece( ChessConstants.NAME_PAWN, color, ChessConstants.VALUE_PAWN, reach);
    return piece;
  }
  
  public Piece createKnight( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( ( Math.abs(y.x - x.x) == 1 && 
                  Math.abs(y.y - x.y) == 2 &&
                  Math.abs(y.z - x.z) == 0 ) ||
                  
                ( Math.abs(y.x - x.x) == 2 && 
                  Math.abs(y.y - x.y) == 1 &&
                  Math.abs(y.z - x.z) == 0 ) ||
                  
                ( Math.abs(y.x - x.x) == 1 && 
                  Math.abs(y.y - x.y) == 0 &&
                  Math.abs(y.z - x.z) == 2 ) ||
                  
                ( Math.abs(y.x - x.x) == 2 && 
                  Math.abs(y.y - x.y) == 0 &&
                  Math.abs(y.z - x.z) == 1 ) ||
                  
                ( Math.abs(y.x - x.x) == 0 && 
                  Math.abs(y.y - x.y) == 2 &&
                  Math.abs(y.z - x.z) == 1 ) ||
                  
                ( Math.abs(y.x - x.x) == 0 && 
                  Math.abs(y.y - x.y) == 1 &&
                  Math.abs(y.z - x.z) == 2 ) ||
                  x.equals(y) );
               
      }
    });
    
    Piece piece = new Piece( ChessConstants.NAME_KNIGHT, color, ChessConstants.VALUE_KNIGHT, reach);
    return piece;
  }
  
  public Piece createBishop( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( ( Math.abs(x.x - y.x) == Math.abs(x.y - y.y) && x.z == y.z ) ||
                ( Math.abs(x.z - y.z) == Math.abs(x.y - y.y) && x.x == y.x ) ||
                ( Math.abs(x.x - y.x) == Math.abs(x.z - y.z) && x.y == y.y ) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_BISHOP, color, ChessConstants.VALUE_BISHOP, reach);
    return piece;
  }
  
  public Piece createRook( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( (x.x == y.x && x.y == y.y) ||
                (x.z == y.z && x.y == y.y) ||
                (x.x == y.x && x.z == y.z) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_ROOK, color, ChessConstants.VALUE_ROOK, reach);
    return piece;
  }
  
  public Piece createQueen( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( // 1D
                (x.x == y.x && x.y == y.y) ||
                (x.z == y.z && x.y == y.y) ||
                (x.x == y.x && x.z == y.z) ||
                // 2D
                ( Math.abs(x.x - y.x) == Math.abs(x.y - y.y) && x.z == y.z ) ||
                ( Math.abs(x.z - y.z) == Math.abs(x.y - y.y) && x.x == y.x ) ||
                ( Math.abs(x.x - y.x) == Math.abs(x.z - y.z) && x.y == y.y ) ||
                // 3D
                ( Math.abs(x.x - y.x) == Math.abs(x.y - y.y) &&
                  Math.abs(x.x - y.x) == Math.abs(x.z - y.z) &&
                  Math.abs(x.y - y.y) == Math.abs(x.z - y.z) ) ||
                x.equals(y) );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_QUEEN, color, ChessConstants.VALUE_QUEEN, reach);
    return piece;
  }
  
  public Piece createKing( final String color ){
    Reachability reach = new Reachability();
    reach.addRule( getFullRange() );
    
    reach.addRule( new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( Math.abs(x.x - y.x) <= 1 && Math.abs(x.y - y.y) <= 1 && Math.abs(x.z - y.z) <= 1 );
      }
    });
    Piece piece = new Piece( ChessConstants.NAME_KING, color, ChessConstants.VALUE_KING, reach);
    return piece;
  }
  
  private ReachabilityRule getFullRange(){
    return new ReachabilityRule() {
      @Override
      public Boolean rule( Location x, Location y ){
        return( x.x >= 0 && x.x < ab.getX() &&
                x.y >= 0 && x.y < ab.getY() &&
                x.z >= 0 && x.z < ab.getZ() ||
                x.equals(y) );
      }
    };
  }

}
