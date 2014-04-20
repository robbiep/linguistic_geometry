package lg.data_objects.trajectory;

import java.util.ArrayList;

import lg.data_objects.location.Location;

public class TrajectoryBundle extends ArrayList<Trajectory> {

  @Override
  public String toString(){
    String outputString = new String();
    for( Trajectory trajectory : this ){
      outputString += trajectory.toString() + "\n";
    }
    if( outputString.length() == 0 ){
      outputString = "No valid trajectories";
    }
    return outputString;
  }
  
  
}
