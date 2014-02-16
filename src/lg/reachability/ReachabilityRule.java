package lg.reachability;

import lg.data_objects.Location;

/**
 * General reachability rule.
 */
public interface ReachabilityRule {
  public Boolean rule( final Location x, final Location y );
}
