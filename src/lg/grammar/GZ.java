package lg.grammar;

import java.util.HashSet;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.zone.Zone;
import lg.zone.ZoneTrajectory;
import lg.zone.ZoneU;

public class GZ {
  
  AbstractBoardGame abg;
  Piece p0;
  Piece q;
  HashSet<Zone> zones;
  Integer[][][] time;
  Integer[][][] next_time;
  
  public GZ( AbstractBoardGame abg, Piece p0, Piece q ){
    this.abg  = abg;
    this.p0   = p0;
    this.q    = q;
    zones     = new HashSet<Zone>();
    maxTime( time );
    maxTime( next_time );
  }

  public void S( ZoneU u, Integer[][][] v, Integer[][][] w ){
    // Q1
    if( abg.abg_ON(u.x).equals(p0) &&
        abg.abg_MAP(p0, u.x, u.y) <= u.l &&
        abg.abg_ON(u.y).equals(q) &&
        abg.abg_OPPOSE(p0, q) ){
      A2( u, v, w );
    } else {
      return;
    }
  }

  private void A2( ZoneU u, Integer[][][] v, Integer[][][] w) {
    // Q2 = T
    t( h(x, y, length), length + 1);
    A3( new Location(), new Location(), 0, v, w );
    distTime( h(x, y, length) );
  }

  private void A3(Location x, Location y, Integer length, Integer[][][] v, Integer[][][] w) {
    // Q3
    if( abg.validLocation(x) || abg.validLocation(y) ){
      A( f(Location x, Location y, Integer length))
    }
  }
  
  private void t(Object h, int i) {
    // TODO Auto-generated method stub
    
  }

  private Object h(Location x0, Location y0, Integer length) {
    // TODO Auto-generated method stub
    return null;
  }
  
  private void maxTime(Integer[][][] time ){
    time = new Integer[abg.getDimX()][abg.getDimY()][abg.getDimZ()];
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          time[x][y][z] = 2 * abg.size();
        }
      }
    }
  }
  
  private void distTime(Object h) {
    
  }
  
  

}
