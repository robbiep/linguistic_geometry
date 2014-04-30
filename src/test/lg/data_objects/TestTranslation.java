package test.lg.data_objects;

import static org.junit.Assert.*;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.translations.Transition;
import lg.data_objects.translations.Translation;
import lg.data_objects.translations.Translations;

import org.junit.Test;

import test.MockData;

public class TestTranslation {

  @Test
  public void testTransitionEquality() {
    Piece p1 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x1 = new Location( 0, 0, 0 );
    Location y1 = new Location( 0, 1, 0 );
    Piece p2 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x2 = new Location( 0, 0, 0 );
    Location y2 = new Location( 0, 1, 0 );
    Transition t1 = new Transition( p1, x1, y1 );
    Transition t2 = new Transition( p2, x2, y2 );
    assert( t1.equals(t2) );
  }
  
  @Test
  public void testTranslationEquality() {
    Piece p1 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x1 = new Location( 0, 0, 0 );
    Location y1 = new Location( 0, 1, 0 );
    Piece p2 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x2 = new Location( 0, 0, 0 );
    Location y2 = new Location( 0, 1, 0 );
    Transition t1 = new Transition( p1, x1, y1 );
    Transition t2 = new Transition( p2, x2, y2 );
    Translation tl1 = new Translation();
    tl1.transition = t1;
    Translation tl2 = new Translation();
    tl2.transition = t2;
    assert( tl1.equals(tl2) );
  }
  
  @Test
  public void testTranslationsPut(){
    Piece p1 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x1 = new Location( 0, 0, 0 );
    Location y1 = new Location( 0, 1, 0 );
    Piece p2 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x2 = new Location( 0, 0, 0 );
    Location y2 = new Location( 0, 1, 0 );
    Transition t1 = new Transition( p1, x1, y1 );
    Transition t2 = new Transition( p2, x2, y2 );
    Translation tl1 = new Translation();
    tl1.transition = t1;
    Translation tl2 = new Translation();
    tl2.transition = t2;
    
    Translations translations = new Translations();
    translations.put( 1, tl1 );
    assert( translations.containsValue( tl1 ) );
    
    assert( translations.put( 1, tl1 ) == null );
    assert( translations.size() == 1 );
    
    assert( translations.put( 1, tl2 ) == null );
    assert( translations.size() == 1 );
    
    Location y3 = new Location( 1, 1, 1 );
    Translation tl3 = new Translation();
    tl3.transition = new Transition( p2, x1, y3 );
    
    assert( translations.put( 1, tl3 ) != null );
    assert( translations.size() == 2 );
    
    assert( translations.put( 1, tl3 ) == null );
    assert( translations.size() == 2 );
  }
  
  @Test
  public void testTransitionToString() {
    Piece p1 = MockData.pieceFactory().createPawn( Color.BLACK );
    Location x1 = new Location( 0, 0, 0 );
    Location y1 = new Location( 0, 1, 0 );
    Transition t1 = new Transition( p1, x1, y1 );
    System.out.println( t1.toString() );
    Translation tl = new Translation();
    System.out.println( tl.toString() );
    tl.transition = t1;
    System.out.println( tl.toString() );
  }

}
