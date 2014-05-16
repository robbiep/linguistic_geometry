package test.project;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Project1;
import project.Project2;
import project.Project3;
import project.Project4;
import project.Project4_MV;

public class TestProject {

  @Test
  public void testProject_1(){
    Project1.run();
  }
  
  @Test
  public void testProject_2(){
    Project2.run();
  }
  
  @Test
  public void testProject_3(){
    Project3.run();
  }
  
  @Test
  public void testProject_4(){
    Project4.run();
  }
  
  @Test
  public void testProject_4_MV(){
    System.out.print( Project4_MV.retiTransitions().toString() );
  }

}
