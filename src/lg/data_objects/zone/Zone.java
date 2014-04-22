package lg.data_objects.zone;

import lg.data_objects.trajectory.Trajectory;

public class Zone {
  ZoneTrajectory main_zone_trajectory;
  ZoneTrajectories zone_trajectories;
  
  public Zone( Trajectory main_trajectory, Integer time ){
    main_zone_trajectory = new ZoneTrajectory( main_trajectory, time );
    zone_trajectories = new ZoneTrajectories();
    zone_trajectories.add( main_zone_trajectory );
  }
  
  public void addTrajectory( Trajectory trajectory, Integer time ){
    zone_trajectories.add( new ZoneTrajectory(trajectory, time) );
  }
  
  public ZoneTrajectory getMainTrajectory(){
    return main_zone_trajectory;
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
