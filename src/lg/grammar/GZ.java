package lg.grammar;

import java.util.HashSet;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.zone.Zone;
import lg.data_objects.zone.ZoneTarget;
import lg.data_objects.zone.ZoneTrajectory;

public class GZ {
  
  AbstractBoardGame abg;
  Piece p0;
  Piece q;
  Location x0;
  Location y0;
  HashSet<Zone> zones;
  Integer[][][] time;
  Integer[][][] next_time;
  
  public GZ( AbstractBoardGame abg, Piece p0, Piece q ){
    this.abg  = abg;
    this.p0   = p0;
    this.q    = q;
  }

  public void S( ZoneTarget u ){
    // Q1
    if( abg.abg_ON(u.x).equals(p0) &&
        abg.abg_MAP(p0, u.x, u.y) <= u.l &&
        abg.abg_ON(u.y).equals(q) &&
        abg.abg_OPPOSE(p0, q) ){
      
      initializeGZ( u );
      A2( u, minTable(), minTable() );
    } else {
      return;
    }
  }

  private void A2( ZoneTarget u, Integer[][][] v, Integer[][][] w) {
    // Q2 = T
    t( h(u), u.l + 1);
    distTime( h(u) );
    A3( new ZoneTarget(), v, w );
  }

  private void A3( ZoneTarget u, Integer[][][] v, Integer[][][] w) {
    // Q3
    if( abg.validLocation(u.x) || abg.validLocation(u.y) ){
      
      ZoneTarget new_u = f(u,v);
      initTime(u);
      A4( new_u, v, w );
      
    } else {
      
      A5( u, v, w );
      
    }
  }

  private void A4( ZoneTarget u, Integer[][][] v, Integer[][][] w) {
    if((  abg.abg_ON(u.x) != null && 
          u.l > 0 &&
          !u.x.equals(x0) &&
          !u.x.equals(y0) &&
          !abg.abg_OPPOSE(p0, abg.abg_ON(u.x)) &&
          abg.abg_MAP(abg.abg_ON(u.x), u.x, u.y) == u.l 
        ) || ( 
          abg.abg_OPPOSE(p0, abg.abg_ON(u.x) ) &&
          abg.abg_MAP(abg.abg_ON(u.x), u.x, u.y) <= u.l )
        ){
      
      t(h(u), time[u.y.getX()][u.y.getY()][u.y.getZ()]);
      Integer[][][] new_w = g( h(u), w );
      alphaTime( h(u),  valueAtLocation( time, u.y ) - u.l + 1);
      A3( u, v, new_w );
      
    } else {
      
      A3( u, v, w );
      
    }
  }

  private void A5(ZoneTarget u, Integer[][][] v, Integer[][][] w) {
    if( isEmptyTable(w) ){
      
      A3( new ZoneTarget(), w, minTable() );
      
    } else {
      // A6 
      // do nothing...
    } 
  }
  
  private void initializeGZ(ZoneTarget u) {
    zones = new HashSet<Zone>();
    x0 = u.x;
    y0 = u.y;
    time = maxTable();
    next_time = maxTable();
  }
  
  private Integer[][][] maxTable() {
    return initTable( 2*abg.size() );
  }
  
  private Integer[][][] minTable() {
    return initTable( 0 );
  }

  private Integer[][][] initTable( int value ){
    Integer[][][] time = new Integer[abg.getDimX()][abg.getDimY()][abg.getDimZ()];
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          time[x][y][z] = value;
        }
      }
    }
    return time;
  }
  
  private ZoneTarget f(ZoneTarget u, Integer[][][] v) {
    if( (( !isLastLocation( u.x ) && ( u.l > 0 ) )
        || (isLastLocation( u.y ) && ( u.l <= 0 ) )) ){
      u.x = getNextLocation( u.x );
    } else {
      u.x = new Location();
      u.y = getNextLocation( u.y );
      u.l = getTime( u.y ) * valueAtLocation( v, u.y );
    }
    return u;
  }

  private Location getNextLocation(Location x) {
    // TODO Auto-generated method stub
    return null;
  }

  private Integer[][][] g(Object h, Integer[][][] w) {
    // TODO Auto-generated method stub
    return null;
  }
  
  private Object h(ZoneTarget u) {
    // TODO Auto-generated method stub
    return null;
  }
  
  private void t(Object h, int i) {
    // TODO Auto-generated method stub
    
  }
  
  private void alphaTime(Object h, int i) {
    // TODO Auto-generated method stub
    
  }
  
  private void distTime(Object h) {
    
  }
  
  private void initTime(ZoneTarget u) {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * @return Value at location's coordinates in table
   * @param table
   * @param location
   */
  private int valueAtLocation( Integer[][][] table, Location location ){
    return table[location.getX()][location.getY()][location.getZ()];
  }

  private int getTime(Location y) {
    // TODO Auto-generated method stub
    return 0;
  }

  
  private boolean isEmptyTable( Integer[][][] time ){
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          if( time[x][y][z] != 0 ){
            return false;
          }
        }
      }
    }
    return true;
  }
  
  private boolean isLastLocation( Location location ){
    return( location.getX() == (abg.getDimX() - 1) &&
            location.getY() == (abg.getDimY() - 1) &&
            location.getZ() == (abg.getDimZ() - 1) );
  }
  
}
