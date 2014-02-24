package test.repl;

import lg.LgREPL;
import repl.Command;
import repl.REPL;

import org.junit.Test;

/**
 * DO NOT INCLUDE IN TEST SUITE! These unit tests require use input!
 * @author Robbie
 *
 */

public class TestREPL {

  @Test 
  public void testREPL(){
    REPL repl = new REPL();
    repl.run();
  }
  
  @Test 
  public void testHelp(){
    REPL repl = new REPL();
    repl.registerCommand( 
        "command", 
        "This is description line 1\n" +
        "This is a long string that will have to be multi-lined " + 
        "or else it will wrap to the left column which is something " + 
        "deemed undesirable for the look, feel, and overall clarity " + 
        "of the help description print out",
        new Command() {
          @Override
          public void execute( String[] args ){
            System.out.println( "command executing..." );
          }
        });
    repl.run();
  }
  
  @Test
  public void testLgREPL(){
    LgREPL repl = new LgREPL();
    repl.run();
  }

}
