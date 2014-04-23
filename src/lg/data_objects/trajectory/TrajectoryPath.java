package lg.data_objects.trajectory;

import java.util.ArrayList;

import lg.data_objects.location.Location;

public class TrajectoryPath extends ArrayList<Location> {
  public TrajectoryPath() {
    super();
  }
  
  public TrajectoryPath( TrajectoryPath trajectoryPath ) {
    for( Location location : trajectoryPath ){
      add( new Location(location) );
    }
  }
}
