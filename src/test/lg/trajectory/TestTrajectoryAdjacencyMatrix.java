package test.lg.trajectory;

import static org.junit.Assert.*;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.Trajectory;
import lg.data_objects.trajectory.TrajectoryAdjacencyMatrix;
import lg.data_objects.trajectory.TrajectoryBundle;
import lg.data_objects.trajectory.TrajectoryBundleGenerator;

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
  public void testIndexToLocation(){
    TrajectoryAdjacencyMatrix adjacencyMatrix = new TrajectoryAdjacencyMatrix( MockData.emptyChestGame() );
    assert( adjacencyMatrix.indexToLocation( 9 ).equals( new Location(1,1,0) ));
    assert( adjacencyMatrix.indexToLocation( 17 ).equals( new Location(1,2,0) ));
    assert( !adjacencyMatrix.indexToLocation( 17 ).equals( new Location(1,2,8) ));
    assert( adjacencyMatrix.indexToLocation( 63 ).equals( new Location(8,8,0) ));
    assert( adjacencyMatrix.indexToLocation( 127 ).equals( new Location(8,8,1) ));
  }
  
  
  @Test
  public void testGetTrajectoryBundle(){
    TrajectoryAdjacencyMatrix adjacencyMatrix = new TrajectoryAdjacencyMatrix( MockData.emptyChestGame() );
    Piece piece = MockData.pieceFactory().createQueen( Color.BLACK );
    adjacencyMatrix.addPath( new Location(1,1,0), new Location(1,2,0) );
    adjacencyMatrix.addPath( new Location(1,2,0), new Location(1,3,0) );
    adjacencyMatrix.addPath( new Location(1,3,0), new Location(1,4,0) );
    adjacencyMatrix.addPath( new Location(1,3,0), new Location(1,5,0) );
    int length = 3;
    TrajectoryBundle bundle = TrajectoryBundleGenerator.generateTrajectoryBundle( adjacencyMatrix, new Location(1,1,0), piece, length );
    assert( bundle.size() == 2 );
    Trajectory trajectory1 = bundle.get( 0 );
    assert( trajectory1.size() == length + 1 );
    System.out.print( bundle.toString() );
  }

}
