package lg;

import java.util.ArrayList;

import lg.data_objects.Location;

/**
 * Represents a 1 to 3 dimensional board of locations.
 * Some locations are "inaccessible", which are described
 * as obstacles.
 * <br><br>
 * Modeling different types connections between the locations
 * is done through the use of external logic.
 */
public class AbstractBoard {
  
  private Integer x_dim;
  private Integer y_dim;
  private Integer z_dim;
  private ArrayList<Location> obstacles;
  private final Integer DEFAULT= 15;
  
  
  // Constructors
  public AbstractBoard(){
    super();
    this.x_dim = DEFAULT;
    this.y_dim = DEFAULT;
    this.z_dim = DEFAULT;
    obstacles = new ArrayList<Location>();
  }
  public AbstractBoard( Integer x_dim ) {
    super();
    
    this.x_dim = x_dim;
    this.y_dim = 1;
    this.z_dim = 1;
    obstacles = new ArrayList<Location>();
  }

  public AbstractBoard( Integer x_dim, 
                        Integer y_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = 1;
    obstacles = new ArrayList<Location>();
  }
  
  public AbstractBoard( Integer x_dim, 
                        Integer y_dim, 
                        Integer z_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
    obstacles = new ArrayList<Location>();
  }

  public AbstractBoard( Integer x_dim, 
                        Integer y_dim, 
                        Integer z_dim,
                        ArrayList<Location> obstacles ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
    this.obstacles = new ArrayList<Location>(obstacles);
  }

  public Integer getX(){
    return x_dim;
  }

  public void setX( Integer x_dim ){
    this.x_dim = x_dim;
  }

  public Integer getY(){
    return y_dim;
  }

  public void setY( Integer y_dim ){
    this.y_dim = y_dim;
  }

  public Integer getZ(){
    return z_dim;
  }

  public void setZ( Integer z_dim ){
    this.z_dim = z_dim;
  }

  public ArrayList<Location> getObstacles(){
    return obstacles;
  }

  public void setObstacles( ArrayList<Location> obstacles ){
    this.obstacles = obstacles;
  }
  
  public void addObstacle( Location obstacle ){
    obstacles.add( obstacle );
  }
  
  public void removeObstacle( Location obstacle ){
    obstacles.remove( obstacle );
  }
  
  public void clearObstacles(){
    obstacles.clear();
  }

  public Boolean validLocation( Location location ){
    return( location.getX() < x_dim && location.getX() >= 0 &&
            location.getY() < y_dim && location.getY() >= 0 &&
            location.getZ() < z_dim && location.getZ() >= 0 &&
            !obstacles.contains(location) );
  }
  
  
}
