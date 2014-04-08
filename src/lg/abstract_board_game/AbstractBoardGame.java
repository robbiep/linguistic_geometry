package lg.abstract_board_game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import chess.ChessPieceFactory;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_structures.GameMap;
import lg.data_structures.GamePiece;
import lg.reachability.ReachabilityTable;
import lg.reachability.Reachability;

public class AbstractBoardGame implements ABG_Functions {
  
  AbstractBoard abstract_board;
  Reachability table_generator;
  GameMap game_map;
  PieceFactory pieceFactory;
  
  public AbstractBoardGame( AbstractBoard ab, PieceFactory pieceFactory ){
    this.abstract_board = ab;
    this.pieceFactory = pieceFactory;
    game_map = new GameMap();
  }
  
  public AbstractBoardGame( Integer x_dim, 
                            Integer y_dim, 
                            Integer z_dim, 
                            PieceFactory pieceFactory ){
    this.abstract_board = new AbstractBoard( x_dim, y_dim, z_dim );
    this.pieceFactory = pieceFactory;
    game_map = new GameMap();
  }

  public AbstractBoard getAbstractBoard(){
    return abstract_board;
  }
  
  public Integer getDimX(){
    return abstract_board.getX();
  }
  
  public Integer getDimY(){
    return abstract_board.getY();
  }
  
  public Integer getDimZ(){
    return abstract_board.getZ();
  }
  
  public Integer size(){
    return getDimX()*getDimY()*getDimZ();
  }

  /**
   * Sets new abstract board. Removes any gameMap no longer in range.
   * @param abstract_board
   */
  public void setAbstractBoard( AbstractBoard abstract_board ){
    this.abstract_board = abstract_board;
    
    // Remove out of range gameMap
    Iterator<Map.Entry<Location,Piece>> it = game_map.entrySet().iterator();
    while( it.hasNext() ){
      if( !abstract_board.validLocation( it.next().getKey() )){
        it.remove();
      }
    }
  }
  
  ///////////////////////////////////////////
  // GamePiece FUNCTIONS
  ///////////////////////////////////////////
  
  public Piece getByLocation( Location location ){
    return game_map.get( location );
  }
  
  public Piece removeByLocation( Location location ){
    return game_map.remove( location );
  }

  public GamePiece[] getAll( ){
    return game_map.getAll();
  }
  
  public GamePiece[] getAllByColor( Color color ){
    ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
    Iterator<Map.Entry<Location,Piece>> it = game_map.entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Location,Piece> entry = it.next();
      if( entry.getValue().getColor() == color ){
        gamePieces.add( new GamePiece( entry.getValue(), entry.getKey() )); 
      }
    }
    return gamePieces.toArray( new GamePiece[gamePieces.size()] );
  }
  
  public void clearAllByColor( Color color ){
    Iterator<Map.Entry<Location,Piece>> it = game_map.entrySet().iterator();
    while( it.hasNext() ){
      Map.Entry<Location,Piece> entry = it.next();
      if( entry.getValue().getColor() == color ){
        it.remove();
      }
    }
  }
  
  public void addPiece( Piece piece, Location location ){
    game_map.put( location, piece );
  }
  
  public void removePiece( Piece piece ){
    game_map.remove( piece );
  }
  
  public void clearPieces(){
    game_map.clear();
  }
 
  public Boolean validLocation( Location location ){
    return( abstract_board.validLocation( location ) );
  }
  
  public Boolean emptyLocation( Location location ){
    return !game_map.containsKey( location );
  }
  
  /**
   * Generates an x by y by z reachability table for a piece
   * @param location location 0 for reachability table
   */
  public ReachabilityTable getReachabilityTable( Piece piece, Location location ){
    return Reachability.generateTable( this, piece, location );
  }
  
  public Integer getDistance( Piece piece,
                              Location current_location,
                              Location target_location){
    return Reachability.getDistance(  this, 
                                      piece, 
                                      current_location, 
                                      target_location );
  }
  
  
  ///////////////////////////////////
  // ABSTRACT BOARD GAME FUNCTIONS //
  ///////////////////////////////////

  @Override
  public Boolean abg_R( Piece piece, 
                        Location current_location,
                        Location target_location ){
    return  piece.isReachable( current_location, target_location ) 
            && validLocation( target_location );
  }

  @Override
  public Piece abg_ON( Location location ){
    return getByLocation( location );
  }

  @Override
  public Boolean abg_TR(  Piece piece, 
                          Location current_location,
                          Location target_location ){
    
    if( abg_ON( current_location ) != null &&
        abg_ON( current_location ).equals(piece) &&
        abg_R( piece, current_location, target_location ) ){
        if( abg_ON( target_location ) == null || 
            abg_OPPOSE( piece,  abg_ON( target_location ) )){
          removeByLocation( target_location );
          removeByLocation( current_location );
          addPiece( piece, target_location );
          return true;
        }
    } 
    return false;
  }

  @Override
  public Integer abg_MAP( Piece piece, 
                          Location current_location,
                          Location target_location ){
    return getDistance( piece, current_location, target_location );
  }

  @Override
  public Set<Location> abg_ST(  Piece piece, 
                                Location current_location, 
                                Integer length ){
    HashSet<Location> locations_st = new HashSet<Location>();
    Integer[][][] r_table = getReachabilityTable( 
        piece, current_location ).getTable();
    for( int x = 0; x < abstract_board.getX(); ++ x ){
      for( int y = 0; y < abstract_board.getY(); ++ y ){
        for( int z = 0; z < abstract_board.getZ(); ++ z ){
          if( r_table[x][y][z] == length ){
            locations_st.add( new Location( x, y, z ) );
          }
        }
      }
    }
    return locations_st;
  }

  // TODO fix for pawn
  @Override
  public Set<Location> abg_SUM( Piece piece, 
                                Location current_location,
                                Location target_location, 
                                Integer length ){
    HashSet<Location> locations_sum = new HashSet<Location>();
    
    ReachabilityTable map_x0 = getReachabilityTable( piece, current_location );
    Piece alt_piece = pieceFactory.createOpposite( piece );
    ReachabilityTable map_y0 = getReachabilityTable( alt_piece, target_location );
    
    Integer[][][] summed_tables = map_x0.add( map_y0 );
    
    for( int x = 0; x < abstract_board.getX(); ++ x ){
      for( int y = 0; y < abstract_board.getY(); ++ y ){
        for( int z = 0; z < abstract_board.getZ(); ++ z ){
          if( summed_tables[x][y][z] == length ){
            locations_sum.add( new Location( x, y, z) );
          }
        }
      }
    }
    return locations_sum;
  }

  @Override
  public Set<Location> abg_MOVE(  Piece piece,
                                  Location start_location, 
                                  Location target_location,
                                  Location current_location,
                                  Integer total_length,
                                  Integer remaining_length ){
    
    Set<Location> locations_move = abg_SUM( piece, start_location, target_location, total_length );
    locations_move.retainAll( abg_ST( piece, current_location, 1 ) );
    locations_move.retainAll( abg_ST( piece, start_location, total_length - remaining_length + 1 ) );
    return locations_move;
  }

  @Override
  public Set<Location> abg_DOCK(  Piece piece, 
                                  Location current_location,
                                  Location target_location, 
                                  Integer length ){
    return abg_SUM( piece, current_location, target_location, length );
  }

  @Override
  public Boolean abg_OPPOSE( Piece piece, Piece piece2 ){
    if( piece.getColor() == Color.OBSTACLE || piece2.getColor() == Color.OBSTACLE ){
      return false;
    } else {
      return piece.getColor() != piece2.getColor();
    }
  }
  

}
