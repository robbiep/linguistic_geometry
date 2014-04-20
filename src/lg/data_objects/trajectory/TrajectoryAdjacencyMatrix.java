package lg.data_objects.trajectory;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class TrajectoryAdjacencyMatrix {
  Piece piece;
  Location root;
  int[][] adjacencyMatrix;
  int x_dim;
  int y_dim;
  int z_dim;
  int dim;
  
  public TrajectoryAdjacencyMatrix( AbstractBoardGame abg ){
    this( abg.getDimX(), abg.getDimY(), abg.getDimZ() );
  }
  
  public TrajectoryAdjacencyMatrix( int x_dim, int y_dim, int z_dim ){
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
    
    dim = x_dim*y_dim*z_dim;
    adjacencyMatrix = new int[dim][dim];
  }
  
  public void addPath( Location parent, Location child ){
    if( parent == null || child == null ){
      return;
    }
    adjacencyMatrix[locationToIndex( parent )][locationToIndex( child )] = 1;
  }
  
  public boolean hasPath( Location parent, Location child ){
    if( parent == null || child == null ){
      return false;
    }
    return( adjacencyMatrix[locationToIndex( parent )][locationToIndex( child )] == 1 );
  }
  
  private int locationToIndex( Location location ){
    return location.getX() 
            + location.getY() * x_dim 
            + location.getZ() * x_dim * y_dim;
  }
  
  public TrajectoryBundle getTrajectoryBundle(){
    TrajectoryBundle trajectoryBundle = new TrajectoryBundle();
    Trajectory trajectory = new Trajectory( piece );
    int root_offset = locationToIndex( root );
    for( int i = 0; i < dim; ++ i ){
      //root_offset
    }
    
    return trajectoryBundle;
  }
  
}
