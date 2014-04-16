package lg.data_objects.trajectory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class TrajectoryGraph {
  
  private class TrajectoryGraphNode {
    public HashSet<TrajectoryGraphNode> parents;
    public HashSet<TrajectoryGraphNode> children;
  }
  
  private Piece piece;
  Location start;
  Location target;
  HashMap<Location,TrajectoryGraphNode> graph_nodes;
  
  public TrajectoryGraph( Piece piece ){
    this.piece = piece;
  }
  
  public boolean add( Location parent, Location next ){
    if( graph_nodes.containsKey( parent ) ){
      TrajectoryGraphNode parent_node = graph_nodes.get( parent );
      parent_node.children.
      return true;
    } else {
      return false;
    }
  }
  
  public int size(){
    if( tail != null ){
      return tail.depth;
    } else {
      return 0;
    }
  }
  
  public TrajectoryBundle getBundle(){
    return null;
  }

}
