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
  
  /**
   * Adds a path from start to end (one direction).
   */
  public void addPath( Location start, Location end ){
    if( start == null || end == null ){
      return;
    }
    adjacencyMatrix[locationToIndex( start )][locationToIndex( end )] = 1;
  }
  
  /**
   * @return True if path exists from start to end
   */
  public boolean hasPath( Location start, Location end ){
    if( start == null || end == null ){
      return false;
    }
    return( adjacencyMatrix[locationToIndex( start )][locationToIndex( end )] == 1 );
  }
  
  /**
   * Returns the value at location [from][to] in the adjacenecy matrix
   */
  public int valueAt( int from, int to ){
    return adjacencyMatrix[from][to];
  }
  
  /**
   * Converts a location to the approriate offset for the adjacency matrix
   */
  public int locationToIndex( Location location ){
    return location.getX() 
            + location.getY() * x_dim 
            + location.getZ() * x_dim * y_dim;
  }
  
  /**
   * @return Number of elements of one dimension of the adjacenct matrix
   */
  public int length(){
    return dim;
  }
  
  /**
   * @return Number of elements in entire adjacency matrix
   */
  public int size(){
    return dim*dim;
  }
  
  public TrajectoryBundle getTrajectoryBundle(){
    TrajectoryBundle trajectoryBundle = new TrajectoryBundle();
    Trajectory trajectory = new Trajectory( piece );
    int root_offset = locationToIndex( root );
    for( int i = 0; i < dim; ++ i ){
      if( i == root_offset ){
        continue;
      } else {
        
      }
    }
    
    return trajectoryBundle;
  }

  public Location indexToLocation( int index ){
    int x = index % x_dim;
    int y = ((index - x) > 0 ) ? (index - x) / x_dim : 0;
    int z = ((index - x - y*x_dim) > 0) ? (index - x - y) / x_dim*y_dim : 0;
    return new Location( x, y, z );
  }
  
}
