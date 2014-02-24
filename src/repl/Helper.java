package repl;

public class Helper {
  public static String[] splitLines( String string ){
    return string.split("\n");
  }
  
  public static String[] splitSubLines( String string, int length ){
    return string.split("(?<=\\G.{" + length + "})");
  }

  /**
   * Splits the space-delimited substrings into an array
   */
  public static String[] splitCommands( String string ){
    return string.split( "\\s+" );
  }
  
  /**
   * Creates a string of spaces of length <b>width</b>
   */
  public static String blankStr( int width ){
    return  new String( new char[width]).replace('\0', ' ');
  }
  
}
