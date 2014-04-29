package lg.data_objects.zone;

import java.util.ArrayList;

import lg.data_objects.Color;

public class ZoneBundle extends ArrayList<Zone>{

  public ZoneBundle getSubZones(Color color ) {
    ZoneBundle newZoneBundle = new ZoneBundle();
    for( Zone oldZone : this ){
      Zone newZone = new Zone();
      for( ZoneTrajectory zoneTrajectory : oldZone.zone_trajectories ){
        if( zoneTrajectory.getPiece().getColor() == color ){
          newZone.addTrajectory( zoneTrajectory );
        }
      }
      newZoneBundle.add( newZone );
    }
    return newZoneBundle;
  }

  public int totalSize() {
    int size = 0;
    for( Zone zone : this ){
      for( ZoneTrajectory zoneTrajectory : zone.getZoneTrajectories() ){
        size += zoneTrajectory.getTrajectory().size();
      }
    }
    return size;
  }

}
