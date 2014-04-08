package lg.reachability;

import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;

public class ReachabilityTable {
  
  private Piece           piece;
  private Integer[][][]   table;
  public  final static Integer  INFINITY = Integer.MAX_VALUE;
  
  public ReachabilityTable( Piece piece, Integer[][][] table ) {
    this.piece = piece;
    this.table = table;
  }
  
  public Integer[][][] getTable(){
    return table;
  }
  
  public Piece getPiece(){
    return piece;
  }
  
  /**
   * @return Value at the location
   */
  public Integer getValue( Location location ){
    return table[location.getX()][location.getY()][location.getZ()];
  }
  
  public Integer[][][] add( ReachabilityTable other_table ){
    int x_dim = table.length;
    int y_dim = table[0].length;
    int z_dim = table[0][0].length;
    Integer[][][] sum_table = new Integer[x_dim][y_dim][z_dim];
    for( int x = 0; x < x_dim; ++ x ){
      for( int y = 0; y < y_dim; ++ y ){
        for( int z = 0; z < z_dim; ++ z ){
          if( table[x][y][z] == INFINITY 
              || other_table.getTable()[x][y][z] == INFINITY ){
            sum_table[x][y][z] = INFINITY;
          } else {
            sum_table[x][y][z] = table[x][y][z] + other_table.getTable()[x][y][z];
          }
        }
      }
    }
    return sum_table;
  }
  
  public void printReachabilityTable(){
      System.out.println( toString() );
  }
  
 /**
  * Prints the 2 dimensional table at a specific offset for dimension Z
  * @param z - offset in dimension z
  */
  public void printReachabilityTable( Integer z ){
    System.out.println( toString( z ) );
  }
  
  @Override
  public String toString(){
    String table_str = "";
    for( int z = 0; z < table[0][0].length; ++ z ){
      table_str += toString( z ) + "\n";
    }
    return table_str;
  }
  
  public String toString( Integer z ){
    String table_str = "";
    for( int y = 0; y < table[0].length; ++ y ){
      for( int x = 0; x < table.length; ++ x ){
        table_str += (table[x][y][z].equals( INFINITY )) ? "x " : table[x][y][z] + " " ;
      }
      table_str += "\n" ;
    }
    return table_str + "\n";
  }
  
}
