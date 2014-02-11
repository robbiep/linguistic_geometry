package lg_cp_1;

import java.util.Scanner;



public class lg_cp_1 {

  public static void main(String[] args) {
    
    Scanner reader = new Scanner(System.in);
    
    System.out.println("Welcome to Linguistic Geometry - CP 1");
    System.out.println("Use default board? (Y continues, anything else requires custom inputs)");
    String default_board = reader.nextLine();
    System.out.println(default_board);
    if( default_board.toLowerCase() == "y" ){
      System.out.println("Default Board!");
    } else {
    
      System.out.println("How many dimensions is the ABG? (Must be 1, 2, or 3)");
      
      int dim = reader.nextInt();
      if( dim < 1 ){
        dim = 1;
      } else if ( dim > 3 ){
        dim = 3;
      }
      
      int x, y, z = 1;
      if( dim >= 1){
        System.out.println("What is the length of dimension X?");
        x = reader.nextInt();
      }
      if( dim >= 2){
        System.out.println("What is the length of dimension Y?");
        y = reader.nextInt();
      }
      if( dim == 3){
        System.out.println("What is the length of dimension Z?");
        z = reader.nextInt();
      }
    }


    reader.close();
  }
}

