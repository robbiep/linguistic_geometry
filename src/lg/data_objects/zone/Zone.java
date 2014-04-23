package lg.data_objects.zone;

import lg.data_objects.trajectory.Trajectory;

public class Zone {
  ZoneTrajectories zone_trajectories;
  
  public Zone() {
    zone_trajectories = new ZoneTrajectories();
  }

  public void addTrajectory( Trajectory trajectory, Integer time ){
    zone_trajectories.add( new ZoneTrajectory(trajectory, time) );
  }
  
  public ZoneTrajectory getMainTrajectory(){
    return zone_trajectories.get( 0 );
  }
  
  public ZoneTrajectories getZoneTrajectories(){
    return zone_trajectories;
  }
  
  @Override
  public String toString(){
    if( zone_trajectories.size() == 0 ){
      return "No trajectories in zone.";
    } else {
      String toString = new String();
      for( ZoneTrajectory zoneTrajectory : zone_trajectories ){
        toString += zoneTrajectory.toString() + "\n";
      }
      return toString;
    }
  }
}
