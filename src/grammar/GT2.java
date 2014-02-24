package grammar;

import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_structures.GamePiece;

// TODO implement with a parser and manage like a real grammar
public class GT2 {
  
  private AbstractBoardGame abg;
  private GamePiece game_piece;
  private Location y0;
  private String trajectory;
  
  public GT2( AbstractBoardGame abg ) {
    super();
    this.abg = abg;
  }
  
  public String GenerateTrajectory( GamePiece game_piece, Location target_location, Integer length){
    this.game_piece = game_piece;
    this.y0 = target_location;
    trajectory = "";
    try{
      S( game_piece.location, target_location, length );
    } catch (Exception e){
      trajectory = "FATAL ERROR: Trajectory invalid.";
    }
    return trajectory;
  }
 

  public void S( Location start_location, Location target_location, Integer length) throws Exception{
    if( abg.abg_MAP( game_piece.piece, start_location, target_location ) <= length &&
        length < 2*(abg.getDimX()*abg.getDimY()*abg.getDimZ()) ){
      A2( start_location, target_location, length );
    } else {
      throw new Exception();
    }
  }
  
  public void A2( Location start_location, Location target_location, Integer length){
    if( abg.abg_MAP( game_piece.piece, start_location, target_location ) != length ){
      A( start_location, med(start_location, target_location, length), lmed(start_location, target_location, length) );
      A( med(start_location, target_location, length), target_location, length - lmed(start_location, target_location, length) );
    } else {
      A( start_location, target_location, length );
    }
  }

  public void A( Location start_location, Location target_location, Integer length){
    if( abg.abg_MAP( game_piece.piece, start_location, target_location ) == length && 
        length > 1 ){
      a(start_location);
      A(next(start_location,length), target_location, f(length));
    } else if ( target_location == y0 ){
      a( target_location );
    } else {
      // Do nothing
    }
  }

  public void a( Location target_location){
    trajectory += "a" + target_location.toString();
  }
  
  public String getTrajectory(){
    return trajectory;
  }
  
  private void printTrajectory(){
    System.out.println( trajectory );
  }
  
  private Integer lmed( Location start_location, Location target_location,
      Integer length ){
    // TODO Auto-generated method stub
    return null;
  }

  private Location med( Location start_location, Location target_location,
      Integer length ){
    // TODO Auto-generated method stub
    return null;
  }
  
  private Location next( Location start_location, Integer length ){
    // TODO Auto-generated method stub
    return null;
  }
  
  private Integer f( Integer length ){
    // TODO Auto-generated method stub
    return null;
  }

}
