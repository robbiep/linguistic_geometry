package lg.data_objects.zone;

import lg.data_objects.trajectory.Trajectory;

// TODO make getters and setters
/**
 * A piece's trajectory tupled with time constraints
 */
public class ZoneTrajectory {
  public Trajectory trajectory;
  public Integer time;
  
  public ZoneTrajectory(){}
  
  public ZoneTrajectory(Trajectory trajectory, Integer time) {
    this.trajectory = trajectory;
    this.time = time;
  }
  
}
