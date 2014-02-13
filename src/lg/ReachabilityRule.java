package lg;

/**
 * General reachability rule.
 */
public interface ReachabilityRule {
  public Boolean rule( final Location x, final Location y );
}
