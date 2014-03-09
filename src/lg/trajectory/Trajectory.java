package lg.trajectory;

import java.util.ArrayList;

import lg.data_objects.Location;
import lg.data_objects.Piece;

public class Trajectory {
  Piece piece;
  ArrayList<Location> trajactory_list;
  
  public Trajectory( Piece piece ) {
    this.piece = piece;
    trajactory_list = new ArrayList<Location>();
  }
  
  public void addLocation( Location location ){
    trajactory_list.add( location );
  }

  public Piece getPiece(){
    return piece;
  }
  
  public ArrayList<Location> getTrajectoryList(){
    return trajactory_list;
  }
  
  public int size(){
    return trajactory_list.size();
  }
  
  @Override
  public String toString(){
    String trajectory_string = "";
    if( size() == 0 ){
      return "Invalid trajectory.";
    } else {
      for( Location location : trajactory_list ){
        trajectory_string += "a" + location.toString();
      }
      return trajectory_string;
    }
  }
}
