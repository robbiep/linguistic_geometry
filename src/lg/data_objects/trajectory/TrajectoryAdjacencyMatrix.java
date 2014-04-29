package lg.data_objects.trajectory;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class TrajectoryAdjacencyMatrix {
  Piece piece;
  Location root;
  int[][] adjacencyMatrix;
  private final AbstractBoardGame abg;
  
  public TrajectoryAdjacencyMatrix( AbstractBoardGame abg ){
    this.abg = abg;
    adjacencyMatrix = new int[length()][length()];
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
   * @return Number of elements of one dimension of the adjacenct matrix
   */
  public int length(){
    return abg.size();
  }
  
  /**
   * @return Number of elements in entire adjacency matrix
   */
  public int size(){
    return length()*length();
  }
  
  public TrajectoryBundle getTrajectoryBundle(){
    TrajectoryBundle trajectoryBundle = new TrajectoryBundle();
    Trajectory trajectory = new Trajectory( piece );
    int root_offset = locationToIndex( root );
    for( int i = 0; i < length(); ++ i ){
      if( i == root_offset ){
        continue;
      } else {
        
      }
    }
    
    return trajectoryBundle;
  }
  
  /**
   * Converts a location to the approriate offset for the adjacency matrix
   */
  public int locationToIndex( Location location ){
    return abg.locationToIndex( location );
  }

  public Location indexToLocation( int index ){
    return abg.indexToLocation( index );
  }
  
}
