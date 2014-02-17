package repl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import chess.ChessPieceFactory;
import lg.AbstractBoard;
import lg.AbstractBoardGame;
import lg.data_objects.Location;
import lg.data_objects.Piece;

public class REPL {
  
  private boolean run;
  private Scanner reader;
  private String command_string;
  private ArrayList<String> command_args;
  private HashMap<String,Command> command_map;
  
  private AbstractBoardGame abg;
  private Location center;
  
  private final Integer DIMENSION_LENGTH = 15;
  private final Integer CENTER = 7;
  
  public REPL( Scanner reader ) {
    super();
    this.run          = true;
    this.reader       = reader;
    this.command_args = new ArrayList<String>();
    this.command_map  = new HashMap<String,Command>();
    this.abg          = new AbstractBoardGame( new AbstractBoard( DIMENSION_LENGTH,
                                                                  DIMENSION_LENGTH,
                                                                  DIMENSION_LENGTH ));
    this.center       = new Location( CENTER, 
                                      CENTER, 
                                      CENTER );
    initializeCommands();
  }
  
  public void println( String message ){
	  System.out.println( message );
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
          "  project    Runs and displays the projects output\n" +
          "             project [number]\n" +
          "             project 1\n" +
          "  reset      Resets the abstract board game. Missing args default to 15\n" +
          "             reset [x dim] [y dim] [z dim]\n" +
          "             reset 15 15 15\n" +
          "  center     Creates a central location value to base reachability on\n"+
          "             center [x] [y] [z]\n" +
          "             center 7 7 7\n" +
          "  obstacle   Adds an obstacle to the abstract board game\n" +
          "             obstacle [x] [y] [z]\n" +
          "             obstacle 10 5 5\n" +
          "  reach      Determine if a piece can reach a given location \n" +
          "             reach [piece] [color] [x] [y] [z]\n" +
          "             reach pawn white 7 7 8\n" +
          "             reach [piece] [color] [a] [b] [c] [x] [y] [z]\n" +
          "             reach pawn white 7 7 7 7 8 7\n" +
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
        System.out.print( "\nCenter: " + 
                          "(" + center.getX() + 
                          ", " + center.getY() + 
                          ", " + center.getZ() + ")" );
        System.out.print( "\nObstacles: " );
        if( !abg.getAbstractBoard().getObstacles().isEmpty() ){
          for( Location obstacle : abg.getAbstractBoard().getObstacles() ){
            System.out.print( "(" + obstacle.getX() + ", " + obstacle.getY() + ", " + obstacle.getZ() + ") " );
          }
        } else {
          System.out.println( "None" );
        }
        System.out.print( "\n" );
      }
    });
    
    command_map.put( "center", new Command() {
      @Override
      public void execute(AbstractBoardGame abg, ArrayList<String> args) {
        if( args.size() == 3 ){
          try{
            // TODO add range validation
            center.setX(Integer.parseInt(args.get(0)));
            center.setY(Integer.parseInt(args.get(1)));
            center.setZ(Integer.parseInt(args.get(2)));
            return;
          } catch( Exception e ){
            // Do nothing
          }
        }
        System.out.println( 
            "Command \"center\" requires 3 arguments specifiying it's x, y, and z positions\n" +
            "    center [x] [y] [z]         sets center to ([x], [y], [z])\n" +
            "    center 5 5 5               sets center to (5, 5, 5)\n"
        );
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
            if( args.get(1).equals( "-a" ) ) {
              abg.getAbstractBoard().clearObstacles();
              return;
            }
            try {
              abg.getAbstractBoard().removeObstacle( new Location(  Integer.parseInt( args.get(1) ), 
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
          "    obstacle [x] [y] [z]       creates an obstacle at ([x], [y], [z])\n" +
          "    obstacle 5 5 5             creates an obstacle at (5, 5, 5)\n" +
          "Optionally two additional subcommands are permitted\n" +
          "    obstacle -r [x] [y] [z]    removes obstacle at postion x, y, z\n" +
          "    obstacle -r -a             removes all obstacles"
        );
      }
    });
    
    command_map.put( "reach", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        if( args.size() == 5 )  {
          try { 
            ChessPieceFactory factory = new ChessPieceFactory( abg.getAbstractBoard() );
            Piece piece = factory.createChessPiece( args.get(0), args.get(1) );
            System.out.println( piece.isReachable( center, new Location(  Integer.parseInt( args.get(2) ), 
                                                      Integer.parseInt( args.get(3) ), 
                                                      Integer.parseInt( args.get(4) ))) );
            
            return;
          } catch( Exception e ){
            // Do nothing
          }
        } else if( args.size() == 8 )  {
          try { 
            ChessPieceFactory factory = new ChessPieceFactory( abg.getAbstractBoard() );
            Piece piece = factory.createChessPiece( args.get(0), args.get(1) );
            System.out.println( piece.isReachable(  new Location( Integer.parseInt( args.get(2) ), 
                                                                  Integer.parseInt( args.get(3) ), 
                                                                  Integer.parseInt( args.get(4) ) ), 
                                                    new Location( Integer.parseInt( args.get(5) ), 
                                                                  Integer.parseInt( args.get(6) ), 
                                                                  Integer.parseInt( args.get(7) ))) );
            return;
          } catch( Exception e ){
            // Do nothing
          }
        }
        System.out.println( 
            "Command \"reach\" requires 5 arguments specifiying the piece, color, and x, y, and z locations\n" +
            "    table [piece] [color] [x] [y] [z]    Determines if a piece can reach location ([x], [y], [z])   \n" +
            "    table pawn white 7 8 7               Determines if a white pawn can reach (7, 8, 7)\n" +
            "Alternatively, \"reach\" requires 8 arguments specifiying the piece, color, and location X and location Y\n" +
            "    table [piece] [color] [a] [b] [c] [x] [y] [z]    Determines if a piece at location ([a], [b], [c]) can reach location ([x], [y], [z])   \n" +
            "    table pawn white 7 7 7 7 8 7               Determines if a white pawn at (7, 7, 7) can reach (7, 8, 7)\n" 
            );
      }
    });
    
    command_map.put( "table", new Command() {
      @Override
      public void execute( AbstractBoardGame abg, ArrayList<String> args ){
        if( args.size() == 2 )  {
          ChessPieceFactory factory = new ChessPieceFactory( abg.getAbstractBoard() );
          Piece piece = factory.createChessPiece( args.get(0), args.get(1) );
          abg.getReachabilityTable( piece, center );
          return;
        }
        System.out.println( 
            "Command \"table\" requires 2 arguments specifiying the piece and color\n" +
            "    table [piece] [color]   \n" +
            "    table pawn white        generates a white pawn reachability table" 
            );
      }
    });
    
    
  }
  
  private void printError(){
    System.out.println( "Invalid command \"" + command_string + "\"" );
  }
  

}
