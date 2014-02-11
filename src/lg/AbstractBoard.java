package lg;

import java.util.ArrayList;

public class AbstractBoard {
  
  private Integer x_dim;
  private Integer y_dim;
  private Integer z_dim;
  private ArrayList<Location> obstacles;
  
  public AbstractBoard(Integer x_dim) {
    super();
    this.x_dim = x_dim;
    this.y_dim = 1;
    this.z_dim = 1;
  }

  public AbstractBoard(Integer x_dim, Integer y_dim) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = 1;
  }
  
  public AbstractBoard(Integer x_dim, Integer y_dim, Integer z_dim) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
  }

  public AbstractBoard(Integer x_dim, Integer y_dim, Integer z_dim,
      ArrayList<Location> obstacles) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
    this.obstacles = obstacles;
  }
  
  
}
