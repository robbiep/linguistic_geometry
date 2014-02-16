package test.chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lg.AbstractBoard;
import lg.data_objects.Location;
import lg.data_objects.Piece;

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
  
  public Integer DIM = 15;
  public Integer CENTER = 7;
  
  @Before
  public void initialize(){
    ab          = new AbstractBoard( DIM, DIM, DIM );
    factory     = new ChessPieceFactory( ab );
    location_x  = new Location( CENTER, CENTER, CENTER );
    valid_locations = new ArrayList<Location>();
  }
  
  @Test
  public void testPawnReachability(){
    piece = factory.createPawn( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
    valid_locations.add( new Location( CENTER, 6, CENTER ) );
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
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
    valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
    valid_locations.add( new Location( CENTER, 8, CENTER ) );
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
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
    valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
    valid_locations.add( new Location( 8,  9,  CENTER  ) );
    valid_locations.add( new Location( 6,  9,  CENTER  ) );
    valid_locations.add( new Location( 8,  5,  CENTER  ) );
    valid_locations.add( new Location( 6,  5,  CENTER  ) );
    valid_locations.add( new Location( 9,  6,  CENTER  ) );
    valid_locations.add( new Location( 9,  8,  CENTER  ) );
    valid_locations.add( new Location( 5,  6,  CENTER  ) );
    valid_locations.add( new Location( 5,  8,  CENTER  ) );
                                                    
    valid_locations.add( new Location( CENTER,  8,  9 ) );
    valid_locations.add( new Location( CENTER,  6,  9 ) );
    valid_locations.add( new Location( CENTER,  8,  5 ) );
    valid_locations.add( new Location( CENTER,  6,  5 ) );
    valid_locations.add( new Location( CENTER,  9,  6 ) );
    valid_locations.add( new Location( CENTER,  9,  8 ) );
    valid_locations.add( new Location( CENTER,  5,  6 ) );
    valid_locations.add( new Location( CENTER,  5,  8 ) );
                                                    
    valid_locations.add( new Location( 8, CENTER,  9 ) );
    valid_locations.add( new Location( 6, CENTER,  9 ) );
    valid_locations.add( new Location( 8, CENTER,  5 ) );
    valid_locations.add( new Location( 6, CENTER,  5 ) );
    valid_locations.add( new Location( 9, CENTER,  6 ) );
    valid_locations.add( new Location( 9, CENTER,  8 ) );
    valid_locations.add( new Location( 5, CENTER,  6 ) );
    valid_locations.add( new Location( 5, CENTER,  8 ) ); 
        
        
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
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
    valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
    for( int i = 0; i < DIM; ++ i ){
      valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
      valid_locations.add( new Location( i, CENTER, CENTER  ) );
      valid_locations.add( new Location( CENTER, i, CENTER  ) );
      valid_locations.add( new Location( CENTER, CENTER, i  ) );
    }
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
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
    valid_locations.add( new Location( 7, 7, 7 ) );
    for( int i = 0; i < DIM; ++ i ){
      valid_locations.add( new Location( i, i, 7  ));
      valid_locations.add( new Location( 14-i, i, 7  ));
      valid_locations.add( new Location( 7, i, i  ));
      valid_locations.add( new Location( 7, 14-i, i  ));
      valid_locations.add( new Location( i, 7, i  ));
      valid_locations.add( new Location( i, 7, 14-i  ));
    }
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                        piece.isReachable( new Location( 7, 7, 7 ), test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                          piece.isReachable( new Location( 7, 7, 7 ), test_location ));
          }
        }
      }
    }
  }
  
  @Test
  public void testQueenReachability(){
    Piece piece = factory.createQueen( ChessConstants.COLOR_BLACK );
    valid_locations.clear();
    for( int i = 0; i < DIM; ++ i ){
      valid_locations.add( new Location( i, i, 7  ));
      valid_locations.add( new Location( 14-i, i, 7  ));
      valid_locations.add( new Location( 7, i, i  ));
      valid_locations.add( new Location( 7, 14-i, i  ));
      valid_locations.add( new Location( i, 7, i  ));
      valid_locations.add( new Location( i, 7, 14-i  ));
    }
    for( int i = 0; i < DIM; ++ i ){
      valid_locations.add( new Location( CENTER, CENTER, CENTER  ) );
      valid_locations.add( new Location( i, CENTER, CENTER  ) );
      valid_locations.add( new Location( CENTER, i, CENTER  ) );
      valid_locations.add( new Location( CENTER, CENTER, i  ) );
    }
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                        piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
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
    for( int i = 6; i < 9; ++ i ){
      for( int j = 6; j < 9; ++ j ){
        for( int k = 6; k < 9; ++ k ){
          valid_locations.add( new Location( i, j, k  ));
        }
      }
    }
    
    for( int i = 0; i < DIM; ++ i ){
      for( int j = 0; j < DIM; ++ j ){
        for( int k = 0; k < DIM; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                        piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( "Fails at (" + test_location.getX() + ", " + test_location.getY() + ", " + test_location.getZ() + ")", 
                          piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
  }

}
