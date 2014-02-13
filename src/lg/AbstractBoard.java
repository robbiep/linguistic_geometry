package lg;

import java.util.ArrayList;

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
  
  
  // Constructors
  public AbstractBoard( Integer x_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = 1;
    this.z_dim = 1;
  }

  public AbstractBoard( Integer x_dim, 
                        Integer y_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = 1;
  }
  
  public AbstractBoard( Integer x_dim, 
                        Integer y_dim, 
                        Integer z_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
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

  
  // Methods
  public Integer getX() {
    return x_dim;
  }

  public Integer getY() {
    return y_dim;
  }

  public Integer getZ() {
    return z_dim;
  }
  
  public Boolean validLocation( Location location ){
    return( location.x <= x_dim && location.x > 0 &&
            location.y <= y_dim && location.y > 0 &&
            location.z <= z_dim && location.z > 0 &&
            !obstacles.contains(location) );
  }
  
  
}
