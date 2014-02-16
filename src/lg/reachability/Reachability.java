package lg.reachability;

import java.util.ArrayList;

import lg.data_objects.Location;

/**
 * These are the rules that define the movement of a LG piece
 * in an ABG. 
 */
public class Reachability {
  
  private ArrayList<ReachabilityRule> rules;
  
  // Constructors
  public Reachability() {
    rules   = new ArrayList<ReachabilityRule>();
  }
  public Reachability( ArrayList<ReachabilityRule> rules ){
    this.rules    = rules;
  }
  
  // Methods
  public void addRule( ReachabilityRule rule ){
    rules.add( rule );
  }
  
  public Boolean isReachable( Location x,
                              Location y ){
    for( ReachabilityRule rule : rules){
      if( !rule.rule( x, y ) ){
        return false;
      }
    }
    return true;
  }
  
}
