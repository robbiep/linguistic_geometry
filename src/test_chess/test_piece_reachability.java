package test_chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lg.AbstractBoard;
import lg.Location;
import lg.Piece;

import org.junit.Before;
import org.junit.Test;

import chess.ChessConstants;
import chess.ChessPieceFactory;

public class test_piece_reachability {

  public AbstractBoard ab;
  public ChessPieceFactory factory;
  public Location location_x; 
  public ArrayList<Location> valid_locations;
  public Piece piece;
  
  @Before
  public void initialize(){
    ab          = new AbstractBoard( 15, 15, 15 );
    factory     = new ChessPieceFactory( ab );
    location_x  = new Location( 8, 8, 8 );
    valid_locations = new ArrayList<Location>();
  }
  
  @Test
  public void testPawnReachability(){
    piece = factory.createPawn( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( 8, 8, 8  ) );
    valid_locations.add( new Location( 8, 7, 8 ) );
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
    
    piece = factory.createPawn( ChessConstants.COLOR_WHITE );
    valid_locations.clear();
    valid_locations.add( new Location( 8, 8, 8  ) );
    valid_locations.add( new Location( 8, 9, 8 ) );
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }
  
  @Test
  public void testKnightReachability(){
    Piece piece = factory.createKnight( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( 8, 8, 8  ) );
    valid_locations.add( new Location( 9,  10, 8  ) );
    valid_locations.add( new Location( 7,  10, 8  ) );
    valid_locations.add( new Location( 9,  6,  8  ) );
    valid_locations.add( new Location( 7,  6,  8  ) );
    valid_locations.add( new Location( 10, 7,  8  ) );
    valid_locations.add( new Location( 10, 9,  8  ) );
    valid_locations.add( new Location( 6,  7,  8  ) );
    valid_locations.add( new Location( 6,  9,  8  ) );
                                                    
    valid_locations.add( new Location( 8,  9,  10 ) );
    valid_locations.add( new Location( 8,  7,  10 ) );
    valid_locations.add( new Location( 8,  9,  6  ) );
    valid_locations.add( new Location( 8,  7,  6  ) );
    valid_locations.add( new Location( 8,  10, 7  ) );
    valid_locations.add( new Location( 8,  10, 9  ) );
    valid_locations.add( new Location( 8,  6,  7  ) );
    valid_locations.add( new Location( 8,  6,  9  ) );
                                                    
    valid_locations.add( new Location( 9,  8,  10 ) );
    valid_locations.add( new Location( 7,  8,  10 ) );
    valid_locations.add( new Location( 9,  8,  6  ) );
    valid_locations.add( new Location( 7,  8,  6  ) );
    valid_locations.add( new Location( 10, 8,  7  ) );
    valid_locations.add( new Location( 10, 8,  9  ) );
    valid_locations.add( new Location( 6,  8,  7  ) );
    valid_locations.add( new Location( 6,  8,  9  ) ); 
        
        
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }
    
  @Test
  public void testRookReachability(){
    Piece piece = factory.createRook( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( 8, 8, 8  ) );
    for( int i = 1; i < 16; ++ i ){
      valid_locations.add( new Location( 8, 8, 8  ) );
      valid_locations.add( new Location( i, 8, 8  ) );
      valid_locations.add( new Location( 8, i, 8  ) );
      valid_locations.add( new Location( 8, 8, i  ) );
    }
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }
  
  @Test
  public void testBishopReachability(){
    Piece piece = factory.createBishop( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( 8, 8, 8  ) );
    for( int i = 1; i < 16; ++ i ){
      valid_locations.add( new Location( i, i, 8  ));
      valid_locations.add( new Location( 16-i, i, 8  ));
      valid_locations.add( new Location( 8, i, i  ));
      valid_locations.add( new Location( 8, 16-i, i  ));
      valid_locations.add( new Location( i, 8, i  ));
      valid_locations.add( new Location( i, 8, 16-i  ));
    }
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                        piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }
  
  @Test
  public void testQueenReachability(){
    Piece piece = factory.createQueen( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          valid_locations.add( new Location( i, j, k  ));
        }
      }
    }
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                        piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }
  
  @Test
  public void testKingReachability(){
    Piece piece = factory.createKing( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    for( int i = 7; i < 10; ++ i ){
      for( int j = 7; j < 10; ++ j ){
        for( int k = 7; k < 10; ++ k ){
          valid_locations.add( new Location( i, j, k  ));
        }
      }
    }
    
    for( int i = 1; i < 16; ++ i ){
      for( int j = 1; j < 16; ++ j ){
        for( int k = 1; k < 16; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                        piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.x + ", " + test_location.y + ", " + test_location.z + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }

}
