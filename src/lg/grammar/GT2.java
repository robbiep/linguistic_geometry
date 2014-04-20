package lg.grammar;

import java.util.ArrayList;
import java.util.Set;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.location.Location;
import lg.data_objects.location.LocationBundle;
import lg.data_objects.piece.Piece;
import lg.data_objects.trajectory.Trajectory;
import lg.data_objects.trajectory.TrajectoryAdjacencyMatrix;
import lg.data_objects.trajectory.TrajectoryBundle;
import lg.data_objects.trajectory.TrajectoryBundleGenerator;
import lg.data_structures.GamePiece;

// TODO implement with a parser and manage like a real grammar
public class GT2 {
  
  private AbstractBoardGame abg;
  private Piece piece;
  private Location x0;
  private Location y0;
  private Integer sub_length;
  private Integer total_length;
  
  private TrajectoryAdjacencyMatrix adjMatrix;
  
  
  public GT2( AbstractBoardGame abg ) {
    super();
    this.abg = abg;
  }
  
  public TrajectoryBundle generateTrajectory( Piece piece, 
                                              Location start,
                                              Location target, 
                                              Integer length){
    init( piece, start, target, length );
    S( start, target, length );
    return TrajectoryBundleGenerator.generateTrajectoryBundle( adjMatrix, start, piece, length );
  }

  private void init(  Piece piece, 
                      Location start,
                      Location target,
                      Integer length ){
    adjMatrix = new TrajectoryAdjacencyMatrix( abg );
    this.piece = piece;
    this.total_length = length;
  }

  private void subsectionInit( Location start, Location target, Integer length ){
    this.x0 = start;
    this.y0 = target;
    this.sub_length = length;
  }

  private void S( Location start, Location target, Integer length ){
    // Q1 = (MAPx,p(y) ≤ l) ∧ (l < 2n)
    if( abg.abg_MAP( piece, start, target ) <= length &&
        length < 2*abg.size() ){
      A_2( start, target, length );
    } else {
      return;
    }
  }
  
  // TODO add index
  private void A_2( Location start, Location target, Integer length ){
    // Q2 = (MAPx,p(y) ≠ l) 
    if( abg.abg_MAP( piece, start, target ) != length ){
      subsectionInit( start, 
                      med( start, target, length ), 
                      lmed( start, target, length ));
      A_3_4_5(  null,
                x0, 
                y0, 
                sub_length );
      Location bridge_y0 = y0;
      subsectionInit( med( start, target, length ), 
                      target, 
                      length - lmed( start, target, length ));
      A_3_4_5(  bridge_y0,
                x0, 
                y0, 
                sub_length );
    // Q2 = F
    } else {
      subsectionInit( start, target, length );
      A_3_4_5( null, start, target, sub_length );
    }
  }

  // TODO add index
  private void A_3_4_5( Location parent, Location start, Location target, Integer length){
    // Q3 = (MAPx,p(y) = l) ∧ (l ≥ 1)
    if( abg.abg_MAP( piece, start, target ) == length && 
        length >= 1 ){
      a( parent, start );
      for( Location next : nextBundle( start, length ) ){
        A_3_4_5(  start, 
                  next, 
                  target, 
                  f( length ));
      }
      
    } 
    
    // Q4 = (y = yo)
    else if ( target.equals(y0) ){
      a( parent, target );
    } 
    
    // Q5 = (y ≠ yo)
    else {
      // Do nothing
    }
  }

  /**
   * Stores a directed edge from parent to child in adjacency matrix
   * @param parent
   * @param child
   */
  private void a( Location parent, Location child ){
    adjMatrix.addPath( parent, child );
  }
  
  
  private Integer lmed( Location start, 
                        Location target,
                        Integer length ){
    return abg.abg_MAP( piece, 
                        start, 
                        med(start, target, length) );
  }

  // TODO add index
  private Location med( Location start, 
                        Location target,
                        Integer length ){
    Set<Location> dock = abg.abg_DOCK(  piece, 
                                        start, 
                                        target, 
                                        length );
    if( dock.size() > 0 ){
      return dock.iterator().next();
    } else {
      return start;
    }
  }

  private LocationBundle nextBundle( Location current_location, Integer remaining_length ){
    Set<Location> move = abg.abg_MOVE(  piece, 
        x0, 
        y0, 
        current_location, 
        sub_length, 
        remaining_length );
    LocationBundle locationBundle = new LocationBundle();
    if( move.size() > 0 ){
      locationBundle.addAll( move );
    } else {
      locationBundle.add( current_location );
    }
    return locationBundle;
  }
  
  // TODO add index
  private Location next( Location current_location, Integer remaining_length ){
    Set<Location> move = abg.abg_MOVE(  piece, 
                                        x0, 
                                        y0, 
                                        current_location, 
                                        sub_length, 
                                        remaining_length );
    
    if( move.size() > 0 ){
      return move.iterator().next();
    } else {
      return current_location;
    }
  }
  
  private Integer f( Integer length ){
    return length - 1;
  }

}
