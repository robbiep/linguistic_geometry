package lg.zone;

import lg.trajectory.Trajectory;

// TODO make getters and setters
public class ZoneTrajectory {
  public Trajectory trajectory;
  public Integer time;
  
  public ZoneTrajectory(){}
  
  public ZoneTrajectory(Trajectory trajectory, Integer time) {
    this.trajectory = trajectory;
    this.time = time;
  }
  
}
