package lg.grammar;

import java.util.Set;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_objects.Piece;
import lg.data_structures.GamePiece;
import lg.trajectory.Trajectory;

// TODO implement with a parser and manage like a real grammar
public class GT2 {
  
  private AbstractBoardGame abg;
  private Piece piece;
  private Location x0;
  private Location y0;
  private Integer sub_length;
  private Integer total_length;
  
  private Trajectory trajectory;
  
  public GT2( AbstractBoardGame abg ) {
    super();
    this.abg = abg;
  }
  
  public Trajectory generateTrajectory( Piece piece, 
                                        Location start,
                                        Location target, 
                                        Integer length){
    init( piece, start, target, length );
    S( start, target, length );
    validateTrajectory();
    return trajectory;
  }

  private void init(  Piece piece, 
                      Location start,
                      Location target,
                      Integer length ){
    this.piece = piece;
    this.total_length = length;
    trajectory = new Trajectory( piece );
  }

  private void subsectionInit( Location start, Location target, Integer length ){
    this.x0 = start;
    this.y0 = target;
    this.sub_length = length;
  }
  
  private void validateTrajectory(){
    if( trajectory.size() != total_length + 1 ){
      trajectory.getTrajectoryList().clear();
    }
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
      subsectionInit( start, med(start, target, length), lmed(start, target, length) );
      A_3_4_5(  x0, 
                y0, 
                sub_length );
      subsectionInit( med(start, target, length), target, length - lmed(start, target, length) );
      A_3_4_5(  x0, 
                y0, 
                sub_length );
    } else {
      subsectionInit( start, target, length );
      A_3_4_5( start, target, sub_length );
    }
  }

  // TODO add index
  private void A_3_4_5( Location start, Location target, Integer length){
    // Q3 = (MAPx,p(y) = l) ∧ (l ≥ 1)
    if( abg.abg_MAP( piece, start, target ) == length && 
        length >= 1 ){
      a(start);
      A_3_4_5(  next(start,length), 
                target, 
                f(length) );
    } 
    
    // Q4 = (y = yo)
    else if ( target.equals(y0) ){
      a( target );
    } 
    
    // Q5 = (y ≠ yo)
    else {
      // Do nothing
    }
  }

  private void a( Location target){
    if( !trajectory.getTrajectoryList().contains( target ) ){
      trajectory.addLocation( target );
    }
  }
  
  public Trajectory getTrajectory(){
    return trajectory;
  }
  
  @Override
  public String toString(){
    return trajectory.toString();
  }
  
  /**
   * Prints the last trajectory to System out
   */
  public void printTrajectory(){
    System.out.print( toString() );
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
