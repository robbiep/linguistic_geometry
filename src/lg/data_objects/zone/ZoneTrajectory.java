package lg.data_objects.zone;

import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.Trajectory;

// TODO make getters and setters
/**
 * A piece's trajectory tupled with time constraints
 */
public class ZoneTrajectory {
  private Trajectory trajectory;
  private Integer time;
  
  public ZoneTrajectory(){}
  
  public ZoneTrajectory(Trajectory trajectory, Integer time) {
    this.trajectory = trajectory;
    this.time = time;
  }
  
  public Piece getPiece(){
    return trajectory.getPiece();
  }
  
  public int getTime(){
    return time;
  }
  
  @Override
  public String toString(){
    return "(" + trajectory.getPiece().getName() + ":" + trajectory.getPiece().getColor() + "," + trajectory.toString() + "," + time + ")";
  }
  
  
}
