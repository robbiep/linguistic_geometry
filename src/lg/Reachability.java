package lg;

import java.util.ArrayList;

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
  public Boolean isReachable( Location x,
                              Location y ){
    for( ReachabilityRule rule : rules){
      if( !rule.rule(x,y) ) {
        return false;
      }
    }
    return true;
  }
  
}
