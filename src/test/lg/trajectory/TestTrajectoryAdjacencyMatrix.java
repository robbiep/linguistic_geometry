package test.lg.trajectory;

import static org.junit.Assert.*;
import lg.data_objects.location.Location;
import lg.data_objects.trajectory.Trajectory;
import lg.data_objects.trajectory.TrajectoryAdjacencyMatrix;
import lg.data_objects.trajectory.TrajectoryBundle;

import org.junit.Test;

import test.MockData;

public class TestTrajectoryAdjacencyMatrix {

  @Test
  public void testAdjacencyMatrix(){
    TrajectoryAdjacencyMatrix adjacencyMatrix = new TrajectoryAdjacencyMatrix( MockData.emptyChestGame() );
    adjacencyMatrix.addPath( new Location(1,1,0), new Location(1,2,0) );
    assert( adjacencyMatrix.hasPath( new Location(1,1,0), new Location(1,2,0) ));
  }
  
  
  @Test
  public void testGetTrajectoryBundle(){
    TrajectoryAdjacencyMatrix adjacencyMatrix = new TrajectoryAdjacencyMatrix( MockData.emptyChestGame() );
    adjacencyMatrix.addPath( new Location(1,1,0), new Location(1,2,0) );
    adjacencyMatrix.addPath( new Location(1,2,0), new Location(1,3,0) );
    adjacencyMatrix.addPath( new Location(1,3,0), new Location(1,4,0) );
    adjacencyMatrix.addPath( new Location(1,3,0), new Location(1,5,0) );
    TrajectoryBundle bundle = adjacencyMatrix.getTrajectoryBundle();
    assert( bundle.size() == 2 );
    Trajectory trajectory1 = bundle.get( 0 );
    assert( trajectory1.size() == 3 );
  }

}
