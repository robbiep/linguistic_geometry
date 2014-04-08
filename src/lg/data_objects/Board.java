package lg.data_objects;

import lg.data_objects.location.Location;

public class Board {
  private Integer x;
  private Integer y;
  private Integer z;
  
  private Integer[][][] board;
  
  public Board( Integer x, Integer y, Integer z ) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
    board = new Integer[x][y][z];
  }
  
  public Integer getX(){
    return x;
  }

  public void setX( Integer x ){
    this.x = x;
  }

  public Integer getY(){
    return y;
  }

  public void setY( Integer y ){
    this.y = y;
  }

  public Integer getZ(){
    return z;
  }

  public void setZ( Integer z ){
    this.z = z;
  }
  
  public Integer get( int x, int y, int z ){
    if( validLocation( x, y, z ) ){
      return board[x][y][z];
    } else {
      return null;
    }
  }
  
  public void set( int x, int y, int z, Integer value ){
    if( validLocation( x, y, z ) ){
      board[x][y][z] = value;
    } 
  }
  
  public void set( Location location, Integer value ){
    set( location.getX(), location.getY(), location.getZ(), value );
  }


  public Boolean validLocation( int x, int y, int z ){
    return( x < this.x && x >= 0 &&
            y < this.y && y >= 0 &&
            z < this.z && z >= 0 );
  }
  
  public Boolean validLocation( Location location ){
    return validLocation( location.getX(), 
                          location.getY(), 
                          location.getZ() );
  }

  
}
