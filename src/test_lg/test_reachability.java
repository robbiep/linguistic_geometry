package test_lg;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lg.Location;
import lg.Reachability;
import lg.ReachabilityRule;

import org.junit.Test;

public class test_reachability {

  @Test
  public void testReachabilityRuleNull() {
    ReachabilityRule rule = new ReachabilityRule(){ 
      @Override
      public Boolean rule(Location x, Location y) {
        return true;
      } 
    };
    assertTrue( "rule.rule does not work", rule.rule(null, null) );
  }
  
  @Test
  public void testReachabilityRuleFunction() {
    ReachabilityRule rule = new ReachabilityRule(){ 
      @Override
      public Boolean rule(Location x, Location y) {
        return ( x.x == y.x );
      } 
    };
    assertTrue( "rule.rule does not work", 
                rule.rule(  new Location(1,1,1), 
                            new Location(1,1,1) ) 
              );
    assertFalse( "rule.rule does not work", 
        rule.rule(  new Location(1,1,1), 
                    new Location(2,1,1) ) 
      );
  }
  
  @Test
  public void testReachabilityDefaultCtor() {
    Reachability reachability = new Reachability();
    assertTrue( "Reachability default Ctor broken", 
                reachability.isReachable(null, null) );
  }
  
  @Test
  public void testReachabilityMultipleRules() {
    ArrayList<ReachabilityRule> rules = new ArrayList<ReachabilityRule>();
    rules.add( new ReachabilityRule(){ 
      @Override
      public Boolean rule(Location x, Location y) {
        return ( x.x == y.x );
      } 
    });
    rules.add( new ReachabilityRule(){ 
      @Override
      public Boolean rule(Location x, Location y) {
        return ( x.y == y.y );
      } 
    });
    Reachability reachability = new Reachability( rules );
    
    assertTrue( "Reachability broken for multiple rules", 
                reachability.isReachable(  
                      new Location(1,1,1), 
                      new Location(1,1,1) )
              );
    
    assertFalse( "Reachability broken for multiple rules", 
        reachability.isReachable(  
              new Location(1,2,1), 
              new Location(1,1,1) )
      );
  }

}
