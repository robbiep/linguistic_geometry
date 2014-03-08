package test.lg.data_objects;

import static org.junit.Assert.*;
import lg.data_objects.Color;

import org.junit.Test;

public class TestColor {

  @Test
  public void testGetOpposite(){
    assertTrue( Color.getOpposite( Color.WHITE ) == Color.BLACK );
    assertTrue( Color.getOpposite( Color.BLACK ) == Color.WHITE );
  }

}
