package repl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <b>Read Evaluate Print Loop</b> (REPL)<br>
 * This is a generalized REPL which allows for runtime registration of commands
 * to be executed. To enable persitence between command execution, the class 
 * must be extended.
 * <br><br>
 * To use, create an instance, register commands, then call run().
 */
public class REPL {
  
  private final Integer LINE_WIDTH    = 80;
  private final Integer MARGIN_WIDTH  = 12;
  private final Integer DESC_WIDTH    = LINE_WIDTH - MARGIN_WIDTH;
  
  protected boolean run;
  protected Scanner reader;
  protected String[] command_args;
  protected HashMap<String,CommandTuple> command_map;
  
  public REPL() {
    this.run          = true;
    this.reader       = new Scanner(System.in);
    this.command_args = new String[0];
    this.command_map  = new HashMap<String,CommandTuple>();
  }
  
  public void run(){
    while( run ){
      getLine();
      executeCommand();
    }
  }
  
  /**
   * Registers a command to be used with REPL. Cannot be removed once added, but 
   * can be overwritten.
   * @param name
   * @param description Newline is forced for lines longer than 50 characters.
   * @param command
   */
  public void registerCommand(  String name, 
                                String description, 
                                Command command ){
    command_map.put( name, new CommandTuple( name, description, command ) );
  }
  
  public void println( String message ){
    System.out.println( message );
  }
  
  private void printCommandError( String command ){
    System.out.println( "Invalid command \"" + command + "\". Enter \"help\" "
        + "for more information." );
  }
  
  private void printHelp(){
    // Iterate the command descriptions
    println("To operate the program use the following commands:\n" +
            "  exit" + Helper.blankStr(6) + "This exits the REPL" );
    
    Iterator<CommandTuple> it = command_map.values().iterator();
    while( it.hasNext() ){
      printCommandDescription(  it.next() );
    }
  }
  
  /**
   * Uses command name to lookup and print description by calling 
   * printCommandDescription( ... )
   * @param command_name
   */
  protected void printCommandDescription( String command_name ){
    CommandTuple command_tuple = command_map.get( command_name );
    if( command_tuple != null ){
      printCommandDescription( command_tuple );
    } else {
      printCommandError( command_name );
    }
  }
  
  /**
   * Prints a formatted command description
   * @param command_tuple
   */
  private void printCommandDescription( CommandTuple command_tuple ){
    // left column is set to width 20
    String left_column = null;
    // Iterate across the description by line, then by sub line of length 60
    String[] lines = Helper.splitLines( command_tuple.description );
    for( int i = 0; i < lines.length; ++ i ){
      String[] sub_lines = Helper.splitSubLines(lines[i], DESC_WIDTH );
      for( int j = 0; j < sub_lines.length; ++ j ){
        // Print name of command for first line only.
        if( i == 0 && j == 0 ){
          left_column = "  " + command_tuple.name;
          left_column += Helper.blankStr( MARGIN_WIDTH - left_column.length() );
        } else {
          left_column = Helper.blankStr( MARGIN_WIDTH );
        }
        // Only trim leading whitespace on wrapped lines
        String right_column = (( j == 0 ) ? sub_lines[j] : sub_lines[j].trim());
        println( left_column + right_column );
      }
    }
  }
  
  
  
  /**
   * Reads the next user input and tokenizes input into it's arguments
   */
  private void getLine(){
    System.out.print( ">> " );
    command_args = Helper.splitCommands( reader.nextLine() );
  }
 
  /**
   * Executes the commands found in command_args<br><br>
   */
  private void executeCommand(){
    executeCommand( command_args );
  }
  
  /**
   * Executes the command_args<br><br>
   * <b><u>Reserved Keywords</u></b><br>
   * <b>exit</b> - exits the program<br>
   * <b>help</b> - prints a help message with command descriptions
   */
  public void executeCommand( String[] command_args ){
    if( command_args[0].equals( "" ) ){
      return;
    } else if( command_args[0].equals( "exit" )){
      run = false;
    } else if( command_args[0].equals( "help" )){
      printHelp();
    } else {
      CommandTuple commandTuple = command_map.get( command_args[0] );
      if( commandTuple != null ){
        commandTuple.command.execute( command_args );
      } else {
        printCommandError( command_args[0] );
      }
    }
  }

}
