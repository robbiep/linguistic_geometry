package repl;

import java.util.HashMap;
import java.util.Scanner;

public class REPL {
  
  protected boolean run;
  protected Scanner reader;
  protected String[] command_args;
  protected HashMap<String,Command> command_map;
  
  public REPL() {
    this.run          = true;
    this.reader       = new Scanner(System.in);
    this.command_args = new String[0];
    this.command_map  = new HashMap<String,Command>();
  }
  
  public void println( String message ){
    System.out.println( message );
  }
  
  private void printCommandError( String command ){
    System.out.println( "Invalid command \"" + command + "\"" );
  }
  
  public void run(){
    while( run ){
      getLine();
      executeCommand();
    }
  }
  
  /**
   * Reads the next user input and tokenizes input into it's arguements
   */
  public void getLine(){
    System.out.print( ">> " );
    command_args = reader.nextLine().split( "\\s+" );
  }
  
  public void registerCommand( String name, Command command ){
    command_map.put( name, command );
  }
 
  public void executeCommand(){
    if( command_args.length == 0 ){
      return;
    } else if( command_args[0].equals( "exit" )){
      run = false;
    } else {
      Command command = command_map.get( command_args[0] );
      if( command != null ){
        command.execute( command_args );
      } else {
        printCommandError( command_args[0] );
      }
    }
  }

}
