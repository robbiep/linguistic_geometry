package lg.data_objects.trajectory;

import java.util.ArrayList;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class Trajectory {
  Piece piece;
  TrajectoryPath trajactory_path;
  
  public Trajectory( Piece piece ) {
    this.piece = piece;
    trajactory_path = new TrajectoryPath();
  }
  
  public Trajectory( Trajectory trajectory ) {
    this.piece = trajectory.piece;
    this.trajactory_path = new TrajectoryPath(trajectory.trajactory_path);
  }

  public void addLocation( Location location ){
    try{
      trajactory_path.add( location );
    } catch( StackOverflowError e ){
      e.printStackTrace();
    }
  }
  
  public void popLocation(){
    if( trajactory_path.size() > 1 ){
      trajactory_path.remove( trajactory_path.size() - 1 );
    }
  }
  
  /**
   * @return Location at index from trajectory
   */
  public Location get( int index ){
    return trajactory_path.get( index );
  }

  public Piece getPiece(){
    return piece;
  }
  
  public TrajectoryPath getTrajectoryPath(){
    return trajactory_path;
  }
  
  public int size(){
    return trajactory_path.size();
  }
  
  @Override
  public String toString(){
    String trajectory_string = "";
    if( size() == 0 ){
      return "Invalid trajectory.";
    } else {
      for( Location location : trajactory_path ){
        trajectory_string += "a" + location.toString();
      }
      return trajectory_string;
    }
  }
  
  @Override
  public boolean equals( Object object ){
    if( object != null && object instanceof Trajectory ){
      Trajectory trajectory = (Trajectory) object;
      return this.trajactory_path.equals(trajectory);
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode(){
    int hash_value = 0;
    for( Location location : trajactory_path ){
      hash_value += location.hashCode()/3;
    }
    return hash_value;
  }
}
