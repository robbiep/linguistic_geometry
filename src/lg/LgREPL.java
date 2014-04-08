package lg;

import project.Project1;
import project.Project2;
import project.Project3;
import repl.Command;
import repl.REPL;
import chess.ChessPieceFactory;
import lg.abstract_board_game.AbstractBoard;
import lg.abstract_board_game.AbstractBoardGame;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.piece.PieceFactory;
import lg.data_structures.GamePiece;
import lg.grammar.GT2;

public class LgREPL extends REPL {

  
  private AbstractBoardGame abg;
  private Location          center;
  private PieceFactory      pieceFactory;
  
  private final Integer     DIMENSION_LENGTH = 15;
  private final Integer     CENTER = 7;
  
  public LgREPL() {
    super();
    pieceFactory = new ChessPieceFactory();
    this.abg = new AbstractBoardGame( DIMENSION_LENGTH,
                                      DIMENSION_LENGTH,
                                      DIMENSION_LENGTH,
                                      pieceFactory );
    this.center = new Location( CENTER, 
                                CENTER, 
                                CENTER );
    initializeCommands();
  }
  
  @Override
  public void run(){
    System.out.print( "Linguitic Geometry REPL\n" +
                      "-----------------------\n\n" +
                      "Type \"help\" to for a list of commands.\n" +
                      "Type \"project 1\" to execute the reachailibty project\n" +
                      "Type \"project 2\" to execute the trajectory project\n" +
                      "Type \"project 3\" to execute the zones project\n" +
                      "Don't use quotes in commands\n\n" );
    super.run();
  }
  
 
  
  private void initializeCommands(){
    registerCommand( "about", 
        "Explains Linguistic Geometry and this program",
        new Command() {
          @Override
          public void execute( String[] args ){
            System.out.println( 
              "Linguistic Geometry is a type of game theory that uses\n" +
              "controlled grammars to generate game strategies for\n" +
              "an abstract board game."
            );       
          }
        });
    
    registerCommand( "info", 
        "Displays information about the abstract board game",
        new Command() {
          @Override
          public void execute( String[] args ){
            System.out.print( 
              "Dimension x = " + abg.getAbstractBoard().getX() +
              "\nDimension y = " + abg.getAbstractBoard().getY() +
              "\nDimension z = " + abg.getAbstractBoard().getZ() 
            );    
            System.out.print( "\nCenter: " + 
                              center.toString() );
            System.out.print( "\nObstacles: " );
            GamePiece[] obstacles = abg.getAllByColor( Color.OBSTACLE );
            if( obstacles.length > 0 ){
              for( GamePiece obstacle : obstacles ){
                System.out.print( "(" + obstacle.location.getX() + 
                                  ", " + obstacle.location.getY() + 
                                  ", " + obstacle.location.getZ() + ") " );
              }
            } else {
              System.out.println( "None" );
            }
            System.out.print( "\n" );
          }
        });
    
    registerCommand( "project",
        "Runs and displays the projects output\n" +
        "    project [number]\n" +                    
        "    project 1",
        new Command() {
          @Override
          public void execute( String[] args ){
            if( args.length == 2 ){
              try{
                if( Integer.parseInt( args[1] ) == 1 ){
                  Project1.run();
                  return;
                } else if( Integer.parseInt( args[1] ) == 2 ){
                  Project2.run();
                  return;
                } else if( Integer.parseInt( args[1] ) == 3 ){
                  Project3.run();
                  return;
                }
              } catch (Exception e){
                println( "Error: " + e.getMessage() );
                return;
              }
            }
         // Invalid command format
            printCommandDescription( "project" );
          }
        });
    
    registerCommand( "reset",
        "Reset the abstract board's dimension. Pieces out of bounds removed\n" +
        "    reset [x dim] [y dim] [z dim]\n" +                             
        "    reset 15 15 15",                                        
        new Command() {
          @Override
          public void execute( String[] args ){
            if( args.length == 4 ){
              try{
                abg.setAbstractBoard( 
                    new AbstractBoard( 
                        Integer.parseInt(args[1]), 
                        Integer.parseInt(args[2]), 
                        Integer.parseInt(args[3]) ));
              } catch (Exception e){
                // Do nothing
              }
            }
            // Invalid command format
            printCommandDescription( "reset" );
          }
        });
    
    registerCommand( "center", 
        "Creates a central location for reachability tables\n" +
        "Takes 3 arguments specifiying it's x, y, and z positions\n" +
        "    center [x] [y] [z]     sets center to ([x], [y], [z])\n" +
        "    center 5 5 5           sets center to (5, 5, 5)\n",                                           
        new Command() {
          @Override
          public void execute(String[] args) {
            if( args.length == 4 ){
              try{
                // TODO add range validation
                center.setX(Integer.parseInt(args[1]));
                center.setY(Integer.parseInt(args[2]));
                center.setZ(Integer.parseInt(args[3]));
                return;
              } catch( Exception e ){
                // Do nothing
              }
            }
            // Invalid command format
            printCommandDescription( "center" );
          }
        });
    
    registerCommand( "obstacle", 
        "Adds an obstacle to the abstract board game\n" +
        "Takes 3 arguments specifiying it's x, y, and z positions\n" +
        "    obstacle [x] [y] [z]       create obstacle at ([x], [y], [z])\n" +
        "    obstacle 5 5 5             create obstacle at (5, 5, 5)\n\n" +
        "Optionally two additional subcommands are permitted\n" +
        "    obstacle -r [x] [y] [z]    removes obstacle at postion x, y, z\n" +
        "    obstacle -r -a             removes all obstacles",
        new Command() {
          @Override
          public void execute( String[] args ){
            if( args.length == 3 )  {
              if( args[1].equals( "-r" ) && args[2].equals( "-a" ) ){
                abg.clearAllByColor( Color.OBSTACLE );
                return;
              }
            } else if( args.length == 4 )  {
              try {
                abg.addPiece( pieceFactory.createObstacle(),  
                              new Location( Integer.parseInt( args[1] ), 
                                            Integer.parseInt( args[2] ), 
                                            Integer.parseInt( args[3] ) ));
                return;
              } catch( Exception e ){
                // Do nothing
              }
            } else if( args.length == 5 ){
              if( args[1].equals( "-r" ) ){
                if( args[2].equals( "-a" ) ) {
                  abg.clearAllByColor( Color.OBSTACLE );
                  return;
                } else {
                  try {
                    Piece piece = abg.getByLocation(  
                        new Location( Integer.parseInt( args[2] ), 
                                      Integer.parseInt( args[3] ), 
                                      Integer.parseInt( args[4] )));
                    if( piece.getColor() == Color.OBSTACLE ){
                      abg.removePiece( piece );
                    }
                    return;
                  } catch( Exception e ){
                    // Do nothing
                  }
                }
              }
            }
            
            // Invalid command format
            printCommandDescription( "obstacle" );
          }
        });
    
    registerCommand( "reach", 
        "Determine if a piece can reach a given location \n" +
        "Takes 5 arguments for the piece, color, and x, y, and z locations\n" +
        "    reach [piece] [color] [x] [y] [z]\n" +
        "    reach pawn white 7 8 7\n" +
        "Alternatively, takes 8 arguments specifiying the piece, color,\n" + 
        "location X and location Y\n" +
        "    reach [piece] [color] [a] [b] [c] [x] [y] [z]\n" + 
        "Piece at location ([a], [b], [c]) reach location ([x], [y], [z])?\n" +
        "    reach pawn white 7 7 7 7 8 7", 
        new Command() {
        @Override
        public void execute( String[] args ){
          if( args.length == 6 )  {
            try { 
              Piece piece = pieceFactory.createPiece( 
                  args[1], 
                  Color.get( args[2] ));
              System.out.println( piece.isReachable( center, 
                  new Location( Integer.parseInt( args[3] ), 
                                Integer.parseInt( args[4] ), 
                                Integer.parseInt( args[5] ))) );
              
              return;
            } catch( Exception e ){
              // Do nothing
            }
          } else if( args.length == 9 )  {
            try { 
              Piece piece = pieceFactory.createPiece( 
                  args[1], Color.get( args[2] ) );
              System.out.println( piece.isReachable(  
                  new Location( Integer.parseInt( args[3] ), 
                                Integer.parseInt( args[4] ), 
                                Integer.parseInt( args[5] ) ), 
                  new Location( Integer.parseInt( args[6] ), 
                                Integer.parseInt( args[7] ), 
                                Integer.parseInt( args[8] ))) );
              return;
            } catch( Exception e ){
              // Do nothing
            }
          }
          // Invalid command format
          printCommandDescription( "reach" );
        }
    });
    
    registerCommand( "table",
        "Displays reachability table for a piece\n" +
        "Takes 2 arguments specifiying the piece and color\n" +
        "    table [piece] [color]   \n" +
        "    table pawn white\n" +
        "Can take additional third argument for the z dimension\n" +
        "    table [piece] [color] [z dimension]  \n" +
        "    table pawn white 7",
        new Command() {
          @Override
          public void execute( String[] args ){
            try{
              if( args.length == 3 )  {
                Piece piece = pieceFactory.createPiece( 
                    args[1], 
                    Color.get( args[2] ) );
                abg.getReachabilityTable( piece, center 
                    ).printReachabilityTable();
                return;
              } else if( args.length == 4 )  {
                Piece piece = pieceFactory.createPiece( 
                    args[1], 
                    Color.get( args[2] ) );
                abg.getReachabilityTable( piece, center 
                    ).printReachabilityTable( Integer.parseInt( args[3] ) );
                return;
                
              }
            } catch (Exception e){
              // Do nothing
            }
            // Invalid command format
            printCommandDescription( "table" );
          }
        });
    
    registerCommand( "traj",
        "Prints the trjactory for a piece from location x to y\n" +
        "    traj [piece] [color] [a] [b] [c] [x] [y] [z] [length]", 
        new Command() {
          @Override
          public void execute( String[] args ){
            if( args.length == 10 ){
              try{
                GT2 gt2 = new GT2( abg );
                gt2.generateTrajectory( 
                    pieceFactory.createPiece( args[1], 
                    Color.get( args[2] ) ),
                    new Location( Integer.parseInt( args[3] ), 
                        Integer.parseInt( args[4] ), 
                        Integer.parseInt( args[5] ) ), 
                    new Location( Integer.parseInt( args[6] ), 
                        Integer.parseInt( args[7] ), 
                        Integer.parseInt( args[8] ) ),
                        Integer.parseInt( args[9] ));
                return;
              } catch (Exception e){
                // Do nothing
              }
            }
            // Invalid command format
            printCommandDescription( "traj" );
          }
        });
    
    
  }
  

}
