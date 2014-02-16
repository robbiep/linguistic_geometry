package lg.reachability;

import lg.data_objects.Dimensions;

public class ReachabilityTable {
  
  private Integer table[][][];
  private Dimensions dimensions;
  private final Integer INFINITY = Integer.MAX_VALUE;
  
  public ReachabilityTable( Integer[][][] table, Dimensions dimensions ) {
    super();
    this.table = table;
    this.dimensions = dimensions;
  }
  
  public Integer[][][] getTable(){
    return table;
  }
  
  public void printReachabilityTable(){
    for( int z = 0; z < dimensions.getZ_dim(); ++ z ){
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
    for( int y = 0; y < dimensions.getY_dim(); ++ y ){
      for( int x = 0; x < dimensions.getX_dim(); ++ x ){
        System.out.print( (table[x][y][z].equals( INFINITY )) ? "x " : table[x][y][z] + " " );
      }
      System.out.print( "\n" );
    }
  }
  
  
}
