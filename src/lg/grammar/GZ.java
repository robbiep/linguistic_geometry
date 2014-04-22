package lg.grammar;

import java.util.HashSet;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.Trajectory;
import lg.data_objects.trajectory.TrajectoryBundle;
import lg.data_objects.trajectory.TrajectoryPath;
import lg.data_objects.zone.Zone;
import lg.data_objects.zone.ZoneBundle;
import lg.data_objects.zone.ZoneTarget;
import lg.data_objects.zone.ZoneTrajectory;

public class GZ {
  
  AbstractBoardGame abg;
  Piece p0;
  Piece q;
  Location x0;
  Location y0;
  Zone zone;// TODO use bundle
  ZoneBundle zones;
  int[][][] time;
  int[][][] next_time;
  
  public GZ( AbstractBoardGame abg, Piece p0, Piece q ){
    this.abg  = abg;
    this.p0   = p0;
    this.q    = q;
  }

  public ZoneBundle S( ZoneTarget u ){
    initializeGZ( u );
    // Q1
    if( abg.abg_ON(u.x).equals(p0) &&
        abg.abg_MAP(p0, u.x, u.y) <= u.l &&
        abg.abg_ON(u.y).equals(q) &&
        abg.abg_OPPOSE(p0, q) ){
      
      
      A2( u, minTable(), minTable() );
      zones.add(zone); // TODO remove
    }
    return zones;
  }

  private void A2( ZoneTarget u, int[][][] v, int[][][] w) {
    // Q2 = T
    Trajectory main_trajectory = h(u);
    zone = new Zone( main_trajectory, u.l );
    t( main_trajectory, u.l + 1);
    distTime( main_trajectory );
    A3( new ZoneTarget(), g( h(u), v ), w );
  }

  private void A3( ZoneTarget u, int[][][] v, int[][][] w) {
    // Q3
    if( !isLastLocation(u.x) || !isLastLocation(u.y) ){
      
      ZoneTarget new_u = f(u,v);
      System.out.println( new_u.toString() );
      initTime(u);
      A4( new_u, v, w );
      
    } else {
      
      A5( u, v, w );
      
    }
  }

  private void A4( ZoneTarget u, int[][][] v, int[][][] w) {
    if((( abg.abg_ON(u.x) != null && 
          u.l > 0 &&
          !u.x.equals(x0) &&
          !u.x.equals(y0) 
         ) && (
           (
             !abg.abg_OPPOSE(p0, abg.abg_ON(u.x)) &&
             abg.abg_MAP(abg.abg_ON(u.x), u.x, u.y) == u.l 
           ) || ( 
             abg.abg_OPPOSE(p0, abg.abg_ON(u.x) ) &&
             abg.abg_MAP(abg.abg_ON(u.x), u.x, u.y) <= u.l )
        ))){
      t(h(u), time[u.y.getX()][u.y.getY()][u.y.getZ()]);
      int[][][] new_w = g( h(u), w );
      alphaTime( u.y, h(u),  valueAtLocation( time, u.y ) - u.l + 1);
      A3( u, v, new_w );
      
    } else {
      
      A3( u, v, w );
      
    }
  }

  private void A5(ZoneTarget u, int[][][] v, int[][][] w) {
    if( !isEmptyTable(w) ){
      
      A3( new ZoneTarget(), w, minTable() );
      
    } else {
      // A6 
      // do nothing...
    } 
  }
  
  private void initializeGZ( ZoneTarget u ){
    zones = new ZoneBundle();
    x0 = u.x;
    y0 = u.y;
    time = maxTable();
    next_time = maxTable();
  }
  
  private int[][][] maxTable() {
    return initTable( 2*abg.size() );
  }
  
  private int[][][] minTable() {
    return initTable( 0 );
  }

  private int[][][] initTable( int value ){
    int[][][] time = new int[abg.getDimX()][abg.getDimY()][abg.getDimZ()];
    for( int x = 0; x < abg.getDimX(); ++ x ){
      for( int y = 0; y < abg.getDimY(); ++ y ){
        for( int z = 0; z < abg.getDimZ(); ++ z ){
          time[x][y][z] = value;
        }
      }
    }
    return time;
  }
  
  private ZoneTarget f( ZoneTarget u, int[][][] v ){
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

  private Location getNextLocation( Location location ){
    if( location.getX() < abg.getDimX() - 1 ){
      location.setX( location.getX() + 1 );
    } else if( location.getY() < abg.getDimY() - 1 ){
      location.setX( 0 );
      location.setY( location.getY() + 1 );
    } else {
      location.setX( 0 );
      location.setY( 0 );
      location.setZ( location.getZ() + 1 );
    }
    return location;
  }

  private int[][][] g( Trajectory h, int[][][] w ){
    if( h == null || h.size() > 2*abg.size() ){
      // w_r = w_r
    } else {
      for( int i = 1; i < h.size(); ++ i ){
        Location location = h.get(i);
        w[location.getX()][location.getY()][location.getZ()] = 1;
      }
    }
    return w;
  }
  
  // TODO add index
  /**
   * @return Trajectory for u
   */
  private Trajectory h( ZoneTarget u ){
    GT2 gt2 = new GT2( abg );
    TrajectoryBundle tracks = gt2.generateTrajectory( abg.abg_ON(u.x), u.x, u.y, u.l );
    if( tracks.size() == 0 ){
      return null;
    } else {
      return tracks.get(0);
    }
  }
  
  private void t( Trajectory trajectory, int time ){
    if( trajectory != null ){
      zone.addTrajectory( trajectory, time );
    }
  }
  
  private void alphaTime( Location location, Trajectory trajectory, int turnTime) {
    if( trajectory != null && trajectory.size() < abg.size() && valueAtLocation( next_time, location ) < abg.size() ){
      setNextTime( location, Math.max( valueAtLocation(next_time, location), turnTime ) );
    } else if( trajectory.size() < abg.size() && valueAtLocation( next_time, location ) >= abg.size() ){
      setNextTime( location, turnTime );
    } else {
      // Do nothing
    }
  }

  private void setNextTime( Location location, int turnTime ){
    next_time[location.getX()][location.getY()][location.getZ()] = turnTime;
  }
  
  private void distTime( Trajectory trajectory ){
    TrajectoryPath trajectoryPath = trajectory.getTrajectoryPath();
    for( int i = 1; i < trajectory.size(); ++ i ){
      setTime( trajectoryPath.get(i), trajectory.size() );
    }
  }
  
  private void setTime( Location location, int turnTime ){
    time[location.getX()][location.getY()][location.getZ()] = turnTime;
  }

  private void initTime( ZoneTarget u ){
    // TODO Auto-generated method stub
    
  }
  
  /**
   * @return Value at location's coordinates in table
   * @param table
   * @param location
   */
  private int valueAtLocation( int[][][] table, Location location ){
    return table[location.getX()][location.getY()][location.getZ()];
  }

  private int getTime( Location location ){
    return valueAtLocation( time, location );
  }

  
  private boolean isEmptyTable( int[][][] time ){
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
    return abg.lastLocation( location );
  }
  
}
