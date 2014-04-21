package lg.data_objects.trajectory;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

/**
 * Walks the adjacency matrix, and returns a Trajectory bundle.
 * @author Robbie
 *
 */
public class TrajectoryBundleGenerator {
  static TrajectoryBundle _trajectoryBundle;
  static Trajectory _currentTrajectory;
  static TrajectoryAdjacencyMatrix _adjacencyMatrix;
  static int _depth;
  static int _length;
  
  public static TrajectoryBundle generateTrajectoryBundle( 
      TrajectoryAdjacencyMatrix adjacencyMatrix,
      Location start, 
      Piece piece, 
      int length ){
    _adjacencyMatrix = adjacencyMatrix;
    _trajectoryBundle = new TrajectoryBundle();
    _currentTrajectory = new Trajectory( piece );
    _depth = 0;
    _length = length;
    
    nextNode( start );
    
    return _trajectoryBundle;
  }

  private static void nextNode( Location location ){
    _currentTrajectory.addLocation( location );
    ++ _depth; 
    if( _depth > _length + 1 ){
      return; // This is an erroneous state.
    }
    boolean pathFound = false;
    int currentLocationOffset = _adjacencyMatrix.locationToIndex( location );
    for( int i = 0; i < _adjacencyMatrix.length(); ++ i ){
      if( i == currentLocationOffset ){
        continue;
      } else if( _adjacencyMatrix.valueAt( currentLocationOffset, i ) == 1 ){
        pathFound = true;
        nextNode( _adjacencyMatrix.indexToLocation( i ) );
        -- _depth;
        _currentTrajectory = new Trajectory( _currentTrajectory );
        _currentTrajectory.popLocation();
      }
    }
    if( !pathFound ){
      if( _depth-1 == _length )
      _trajectoryBundle.add( _currentTrajectory );
    }
  }
}
