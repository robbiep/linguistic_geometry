package test.repl;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.Scanner;

import lg.LgREPL;

import org.junit.Test;

public class TestREPL {

  @Test
  public void test(){
    LgREPL repl = new LgREPL();
    repl.run();
  }

}
