package lg.data_objects.trajectory;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class TrajectoryTree {
  Piece piece;
  JTree trajectoryTree;
  DefaultMutableTreeNode root;
  
  TrajectoryTree( Piece piece, Location start ){
    this.piece = piece;
    root = new DefaultMutableTreeNode( start );
    trajectoryTree = new JTree( root );
  }
}
