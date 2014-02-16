package repl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import chess.ChessPieceFactory;
import lg.AbstractBoardGame;
import lg.data_objects.Location;

public class REPL {
  
  private boolean run;
  private Scanner reader;
  private String command_string;
  private ArrayList<String> command_args;
  private HashMap<String,Command> command_map;
  
  private AbstractBoardGame abg;
  
  public REPL( Scanner reader ) {
    super();
    this.run          = true;
    this.reader       = reader;
    this.command_args = new ArrayList<String>();
    this.command_map  = new HashMap<String,Command>();
    this.abg          = new AbstractBoardGame();
    initializeCommands();
  }
  
  public void run(){
    System.out.print( "Linguitic Geometry REPL\n" +
                      "-----------------------\n\n" +
                      "Type \"help\" to for a list of commands.\n\n" );
    while( run ){
      getLine();
      executeCommand();
    }
  }

  public void getLine(){
    System.out.print( ">> " );
    String command = reader.nextLine();
    StringTokenizer tokenizer = new StringTokenizer( command );
    command_args.clear();
    if( tokenizer.hasMoreTokens() ){
      command_string = tokenizer.nextToken();
      while( tokenizer.hasMoreTokens() ){
        command_args.add( tokenizer.nextToken() );
      }
    } else {
      command = "";
    }
  }
  
  public void registerCommand( String name, Command command ){
    command_map.put( name, command );
  }
 
  public void executeCommand(){
    if( command_string.equals( "exit" )){
      run = false;
    } else {
      Command command = command_map.get( command_string );
      if( command != null ){
        command.execute( abg, command_args );
      } else {
        printError();
      }
    }
  }
  
  private void initializeCommands(){
    command_map.put( "help", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        // TODO consider adding command specific help
        System.out.print( 
          "Commands to operate the program:\n" +
          "  exit       This exits the REPL\n" +
          "  about      Explains Linguistic Geometry and this program\n" +
          "  info       Displays information about the abstract board game\n" + 
          "  reset      Resets the abstract board game. Missing args default to 1\n" +
          "             reset [x dim] [y dim] [z dim]\n" +
          "             reset 15 15 15\n" +
          "  obstacle   Adds an obstacle to the abstract board game\n" +
          "             obstacle [x] [y] [z]\n" +
          "             obstacle 10 5 5\n" +
          "  reach      Determine if a piece can reach a given location \n" +
          "             reach [piece] [color] [x] [y] [z]\n" +
          "             reach pawn white 7 7 8\n" +
          "  table      Displays reachability table for a piece\n" +
          "             table [piece] [color]\n" +
          "             table pawn white\n"
        );       
      }
    });
    
    command_map.put( "about", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        System.out.println( 
          "Linguistic Geometry is a type of game theory that uses controlled grammars\n" +
          "to generate game strategies for an abstract board game."
        );       
      }
    });
    
    command_map.put( "info", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        System.out.print( 
          "Dimension x = " + abg.getAbstractBoard().getX() +
          "\nDimension y = " + abg.getAbstractBoard().getY() +
          "\nDimension z = " + abg.getAbstractBoard().getZ() 
        );       
        if( !abg.getAbstractBoard().getObstacles().isEmpty() ){
          System.out.print( "\nObstacles: " );
          for( Location obstacle : abg.getAbstractBoard().getObstacles() ){
            System.out.print( "(" + obstacle.getX() + ", " + obstacle.getY() + ", " + obstacle.getZ() + ") " );
          }
        }
        System.out.print( "\n" );
      }
    });
    
    command_map.put( "obstacle", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        if( args.size() == 2 )  {
          if( args.get(0).equals( "-r" ) && args.get(1).equals( "-a" ) ){
            abg.getAbstractBoard().clearObstacles();
            return;
          }
        } else if( args.size() == 3 )  {
          try {
            abg.getAbstractBoard().addObstacle( new Location( Integer.parseInt( args.get(0) ), 
                                                              Integer.parseInt( args.get(1) ), 
                                                              Integer.parseInt( args.get(2) )));
            return;
          } catch( Exception e ){
            // Do nothing
          }
        } else if( args.size() == 4 ){
          if( args.get(0).equals( "-r" ) ){
            try {
              abg.getAbstractBoard().removeObstacle( new Location( Integer.parseInt( args.get(1) ), 
                                                                Integer.parseInt( args.get(2) ), 
                                                                Integer.parseInt( args.get(3) )));
              return;
            } catch( Exception e ){
              // Do nothing
            }
          }
        }
          
        System.out.println( 
              "Command \"obstacle\" requires 3 arguments specifiying it's x, y, and z positions\n" +
              "    obstacle [x] [y] [z]       creates an obstacle at (x, y, z)\n" +
              "    obstacle 5 5 5             creates an obstacle at (5, 5, 5)\n" +
              "Optionally two additional subcommands are permitted\n" +
              "    obstacle -r [x] [y] [z]    removes obstacle at postion x, y, z\n" +
              "    obstacle -r -a             removes all obstacles"
              
              );
        
      }
    });
    
    command_map.put( "table", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        if( args.size() == 2 )  {
        //  abg.getReachabilityTable( ChessPieceFactory, location );
        }
        System.out.println( 
            "Command \"table\" requires 2 arguments specifiying the piece and color\n" +
            "    table [piece] [color]   \n" +
            "    table pawn white        generates a white pawn reachability table\n" 
            
            );
      }
    });
    
    
  }
  
  private void printError(){
    System.out.println( "Invalid command \"" + command_string + "\"" );
  }
  

}
