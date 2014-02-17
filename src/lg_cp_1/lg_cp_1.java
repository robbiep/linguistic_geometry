package lg_cp_1;

import java.util.Scanner;

import repl.REPL;



public class lg_cp_1 {

  public static void main(String[] args) {
    
    Scanner reader = new Scanner(System.in);
    String lg_cp1_message = "Welcome to Linguistic Geometry - CP 1\n" +
    						"-------------------------------------\n" +
    						"Initial settings - 15 x 15 x 15 board, center at (7, 7, 7)\n" +
    						"Enter command \"project 1\" to execute first project (no quotes).\n\n";
    
    REPL lg_repl = new REPL( reader );
    lg_repl.println( lg_cp1_message );
    lg_repl.run();
    
    reader.close();
  }
}

