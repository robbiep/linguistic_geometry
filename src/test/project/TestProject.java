package test.project;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Project1;
import project.Project2;
import project.Project3;

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

}
