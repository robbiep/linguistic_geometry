package lg.data_objects.zone;

import java.util.HashSet;

import lg.data_objects.trajectory.Trajectory;

public class Zone {
  ZoneTrajectory main_zone_trajectory;
  HashSet<ZoneTrajectory> zone_trajectories;
  
  public Zone( Trajectory main_trajectory, Integer time ){
    this.main_zone_trajectory.trajectory = main_trajectory;
    this.main_zone_trajectory.time = time;
    zone_trajectories = new HashSet<ZoneTrajectory>();
    zone_trajectories.add( main_zone_trajectory );
  }
  
  public void addTrajectory( Trajectory trajectory, Integer time ){
    zone_trajectories.add( new ZoneTrajectory(trajectory, time) );
  }
  
  public ZoneTrajectory getMainTrajectory(){
    return main_zone_trajectory;
  }
  
  public HashSet<ZoneTrajectory> getZoneTrajectories(){
    return zone_trajectories;
  }
  

}
