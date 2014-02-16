package test.chess;

import static org.junit.Assert.*;
import lg.data_objects.Location;

import org.junit.Test;

public class test_location {

  @Test
  public void testLocationEquals(){
    Location location_1 = new Location(8,8,8);
    Location location_2 = new Location(8,8,8);
    assertTrue( location_1.equals( location_2 ) );
  }

}
