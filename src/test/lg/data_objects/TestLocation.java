package test.lg.data_objects;

import static org.junit.Assert.*;
import lg.data_objects.location.Location;

import org.junit.Test;

public class TestLocation {

  @Test
  public void testLocationEquals(){
    Location location_1 = new Location(7,7,0);
    Location location_2 = new Location(7,7,0);
    assertTrue( location_1.equals( location_2 ) );
  }

}
