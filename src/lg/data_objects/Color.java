package lg.data_objects;

public enum Color {
  OBSTACLE,
  WHITE,
  BLACK;
  
  public static Color get( String color_str ){
    return Color.valueOf( color_str.toUpperCase() );
  }
}
