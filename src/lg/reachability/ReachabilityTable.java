package lg.reachability;

import lg.data_objects.Piece;

public class ReachabilityTable {
  
  private final Piece           piece;
  private final Integer[][][]   table;
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
  
  public void printReachabilityTable(){
    for( int z = 0; z < table[0][0].length; ++ z ){
      printTwoDimTable( z );
      System.out.print( "\n\n" );
    }
  }
  
 /**
  * Prints the 2 dimensional table at a specific offset for dimension Z
  * @param z - offset in dimension z
  */
  public void printTwoDimTable( Integer z ){
    System.out.println( "Z dimension = " + z );
    for( int y = 0; y < table[0].length; ++ y ){
      for( int x = 0; x < table.length; ++ x ){
        System.out.print( (table[x][y][z].equals( INFINITY )) ? "x " : table[x][y][z] + " " );
      }
      System.out.print( "\n" );
    }
  }
  
  
}
