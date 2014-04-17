package lg.data_objects.trajectory;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class TrajectoryAdjacencyMatrix {
  Piece piece;
  Location root;
  int[][] adjacencyMatrix;
  int x_dim;
  int y_dim;
  int z_dim;
  
  public TrajectoryAdjacencyMatrix( int x_dim, int y_dim, int z_dim ){
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
    adjacencyMatrix = new int[x_dim*y_dim*z_dim][x_dim*y_dim*z_dim];
  }
  
  public void addPath( Location parent, Location child ){
    adjacencyMatrix[locationToIndex( parent )][locationToIndex( child )] = 1;
  }
  
  private int locationToIndex( Location location ){
    return location.getX() 
            + location.getY() * x_dim 
            + location.getZ() * x_dim * y_dim;
  }
  
  TrajectoryBundle getTrajectoryBundle(){
    TrajectoryBundle trajectoryBundle = new TrajectoryBundle();
    Trajectory trajectory = new Trajectory( piece );
    
    
    return trajectoryBundle;
  }
  
}
