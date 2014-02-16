package test.repl;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.Scanner;

import org.junit.Test;

import repl.REPL;

public class test_REPL {

  @Test
  public void test(){
    Scanner reader = new Scanner(System.in);
    REPL repl = new REPL( reader );
    repl.run();
  }

}
