package test.lg.trajectory;

import static org.junit.Assert.*;
import lg.data_objects.Color;
import lg.data_objects.Location;
import lg.trajectory.Trajectory;

import org.junit.Test;

import test.MockData;

public class TestTrajectory {

  @Test
  public void testCreateTrajectory(){
    Trajectory trajectory = new Trajectory( 
        MockData.pieceFactory().createPawn( Color.WHITE  ) );
    
    trajectory.addLocation( new Location( 0, 0, 0 ) );
    trajectory.addLocation( new Location( 0, 1, 0 ) );
    trajectory.addLocation( new Location( 0, 2, 0 ) );
  }
  
  @Test
  public void testTrajectoryLength(){
    Trajectory trajectory = new Trajectory( 
        MockData.pieceFactory().createPawn( Color.WHITE  ) );
    
    trajectory.addLocation( new Location( 0, 0, 0 ) );
    trajectory.addLocation( new Location( 0, 1, 0 ) );
    trajectory.addLocation( new Location( 0, 2, 0 ) );
    
    assertTrue( trajectory.size() == 3 );
  }
  
  @Test
  public void testTrajectoryString(){
    Trajectory trajectory = new Trajectory( 
        MockData.pieceFactory().createPawn( Color.WHITE  ) );
    
    trajectory.addLocation( new Location( 0, 0, 0 ) );
    trajectory.addLocation( new Location( 0, 1, 0 ) );
    trajectory.addLocation( new Location( 0, 2, 0 ) );
    
    assertTrue( trajectory.toString().equals( "(0, 0, 0)(0, 1, 0)(0, 2, 0)" ));
  }

}
