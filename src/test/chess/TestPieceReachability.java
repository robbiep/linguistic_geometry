package test.chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lg.abstract_board_game.AbstractBoard;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

import org.junit.Before;
import org.junit.Test;

import test.MockData;
import chess.ChessConstants;
import chess.ChessPieceFactory;

public class TestPieceReachability {

  public AbstractBoard ab;
  public ChessPieceFactory factory;
  public Location location_x; 
  public ArrayList<Location> valid_locations;
  public Piece piece;
  
  @Before
  public void initialize(){
    ab          = MockData.abstractBoard();
    factory     = MockData.pieceFactory();
    location_x  = MockData.centerLocation();
    valid_locations = new ArrayList<Location>();
  }
  
  @Test
  public void testPawnReachability(){
    piece = factory.createPawn( Color.BLACK );
    valid_locations.clear();
    valid_locations.add( MockData.centerLocation() );
    valid_locations.add( new Location( MockData.CENTER, 6, MockData.CENTER ) );
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
          Location test_location = new Location( i, j, k );
          if( valid_locations.contains( test_location ) ){
            assertTrue( piece.isReachable( location_x, test_location ));
          } else {
            assertFalse( piece.isReachable( location_x, test_location ));
          }
        }
      }
    }
    
    piece = factory.createPawn( Color.WHITE );
    valid_locations.clear();
    valid_locations.add( MockData.centerLocation() );
    valid_locations.add( new Location( MockData.CENTER, 8, MockData.CENTER ) );
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
    Piece piece = factory.createKnight( Color.BLACK );
    valid_locations.clear();
    valid_locations.add( MockData.centerLocation() );
    valid_locations.add( new Location( 8,  9,  MockData.CENTER  ) );
    valid_locations.add( new Location( 6,  9,  MockData.CENTER  ) );
    valid_locations.add( new Location( 8,  5,  MockData.CENTER  ) );
    valid_locations.add( new Location( 6,  5,  MockData.CENTER  ) );
    valid_locations.add( new Location( 9,  6,  MockData.CENTER  ) );
    valid_locations.add( new Location( 9,  8,  MockData.CENTER  ) );
    valid_locations.add( new Location( 5,  6,  MockData.CENTER  ) );
    valid_locations.add( new Location( 5,  8,  MockData.CENTER  ) );
                                                    
    valid_locations.add( new Location( MockData.CENTER,  8,  9 ) );
    valid_locations.add( new Location( MockData.CENTER,  6,  9 ) );
    valid_locations.add( new Location( MockData.CENTER,  8,  5 ) );
    valid_locations.add( new Location( MockData.CENTER,  6,  5 ) );
    valid_locations.add( new Location( MockData.CENTER,  9,  6 ) );
    valid_locations.add( new Location( MockData.CENTER,  9,  8 ) );
    valid_locations.add( new Location( MockData.CENTER,  5,  6 ) );
    valid_locations.add( new Location( MockData.CENTER,  5,  8 ) );
                                                    
    valid_locations.add( new Location( 8, MockData.CENTER,  9 ) );
    valid_locations.add( new Location( 6, MockData.CENTER,  9 ) );
    valid_locations.add( new Location( 8, MockData.CENTER,  5 ) );
    valid_locations.add( new Location( 6, MockData.CENTER,  5 ) );
    valid_locations.add( new Location( 9, MockData.CENTER,  6 ) );
    valid_locations.add( new Location( 9, MockData.CENTER,  8 ) );
    valid_locations.add( new Location( 5, MockData.CENTER,  6 ) );
    valid_locations.add( new Location( 5, MockData.CENTER,  8 ) ); 
        
        
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
    Piece piece = factory.createRook( Color.BLACK );
    valid_locations.clear();
    valid_locations.add( MockData.centerLocation() );
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      valid_locations.add( MockData.centerLocation() );
      valid_locations.add( new Location( i, MockData.CENTER, MockData.CENTER  ) );
      valid_locations.add( new Location( MockData.CENTER, i, MockData.CENTER  ) );
      valid_locations.add( new Location( MockData.CENTER, MockData.CENTER, i  ) );
    }
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
    Piece piece = factory.createBishop( Color.BLACK );
    valid_locations.clear();
    valid_locations.add( new Location( 7, 7, 7 ) );
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      valid_locations.add( new Location( i, i, 7  ));
      valid_locations.add( new Location( 14-i, i, 7  ));
      valid_locations.add( new Location( 7, i, i  ));
      valid_locations.add( new Location( 7, 14-i, i  ));
      valid_locations.add( new Location( i, 7, i  ));
      valid_locations.add( new Location( i, 7, 14-i  ));
    }
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
    Piece piece = factory.createQueen( Color.BLACK );
    valid_locations.clear();
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      valid_locations.add( new Location( i, i, 7  ));
      valid_locations.add( new Location( 14-i, i, 7  ));
      valid_locations.add( new Location( 7, i, i  ));
      valid_locations.add( new Location( 7, 14-i, i  ));
      valid_locations.add( new Location( i, 7, i  ));
      valid_locations.add( new Location( i, 7, 14-i  ));
    }
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      valid_locations.add( MockData.centerLocation() );
      valid_locations.add( new Location( i, MockData.CENTER, MockData.CENTER  ) );
      valid_locations.add( new Location( MockData.CENTER, i, MockData.CENTER  ) );
      valid_locations.add( new Location( MockData.CENTER, MockData.CENTER, i  ) );
    }
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
    Piece piece = factory.createKing( Color.BLACK );
    valid_locations.clear();
    for( int i = 6; i < 9; ++ i ){
      for( int j = 6; j < 9; ++ j ){
        for( int k = 6; k < 9; ++ k ){
          valid_locations.add( new Location( i, j, k  ));
        }
      }
    }
    
    for( int i = 0; i < MockData.DIMENSION; ++ i ){
      for( int j = 0; j < MockData.DIMENSION; ++ j ){
        for( int k = 0; k < MockData.DIMENSION; ++ k ){
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
