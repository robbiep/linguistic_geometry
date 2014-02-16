package lg.data_objects;

public class Dimensions {
  private Integer x_dim;
  private Integer y_dim;
  private Integer z_dim;
  
  //Constructors
  public Dimensions( Integer x_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = 1;
    this.z_dim = 1;
  }
  
  public Dimensions( Integer x_dim, 
                     Integer y_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = 1;
  }
  
  public Dimensions( Integer x_dim, 
                     Integer y_dim, 
                     Integer z_dim ) {
    super();
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    this.z_dim = z_dim;
  }

  public Integer getX_dim(){
    return x_dim;
  }

  public void setX_dim( Integer x_dim ){
    this.x_dim = x_dim;
  }

  public Integer getY_dim(){
    return y_dim;
  }

  public void setY_dim( Integer y_dim ){
    this.y_dim = y_dim;
  }

  public Integer getZ_dim(){
    return z_dim;
  }

  public void setZ_dim( Integer z_dim ){
    this.z_dim = z_dim;
  }

}
