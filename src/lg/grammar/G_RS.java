package lg.grammar;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.translations.Translation;
import lg.data_objects.translations.Translations;
import lg.data_objects.zone.ZoneBundle;

public class G_RS {
  
  private AbstractBoardGame abg;
  private Piece p0;
  private Piece q;

  private Translations translations;
  
  private int SIGN = 1;
  private int END  = 1;
  private int d = 0;
  private Location x0;
  private Location y0;
  
  
  public G_RS( AbstractBoardGame abg, Piece p0, Piece q, Location x, Location y ){
    this.abg = abg;
    this.p0 = p0;
    this.q = q;
    this.x0 = x;
    this.y0 = y;
    translations = new Translations();
  }
  
  public Translations executeGrammar(){
    return S( 0 );
  }
  
  public Translations S( int i ){
    translations.put( i, new Translation() );
    A2( i );
    return translations;
  }

  private void A2( int i ){
    G_Z gz = new G_Z( abg, p0, q, x0, y0 );
    ZoneBundle zoneBundle = gz.executeGrammar();
    if( zoneBundle.size() > 0 && d < 10 ){
      Translation next_translation = new Translation();
      next_translation.parent = i;
      Translation this_translation = translations.get( i );
      if( this_translation.child != 0 ){
        Translation sibling_translation = translations.get( this_translation.child );
        sibling_translation.sibling = END;
        translations.put( this_translation.child, sibling_translation );
      } else {
        this_translation.sibling = 0;
        this_translation.child = 0;
      }
      translations.put( i, this_translation );
      translations.put( END, next_translation );
      ++ END;
      ++ d;
      SIGN = SIGN * -1;
      A2( END );
      pi( END );
      A2( END );
    } else {
      A3( i );
    }
    
  }

  private void A3( int i ){
    if( d > 0 ){
      SIGN = -1 * SIGN;
      //d = d - 1;
    }
  }

  private void pi( int i ){
    // TODO Auto-generated method stub
    
  }

}
