package test.repl;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.Scanner;

import org.junit.Test;

import repl.LgREPL;

public class TestREPL {

  @Test
  public void test(){
    Scanner reader = new Scanner(System.in);
    LgREPL repl = new LgREPL( reader );
    repl.run();
  }

}
