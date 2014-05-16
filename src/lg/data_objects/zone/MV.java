package lg.data_objects.zone;

import java.util.ArrayList;

import lg.data_objects.translations.Transition;

public class MV {
  private int position = 0;
  
  ArrayList<Transition> transitions;
  
  public MV() {
    transitions = new ArrayList<>();
  }
  
  public MV( ArrayList<Transition> transitions ){
    setTransitions( transitions );
  }
  
  public void setTransitions( ArrayList<Transition> transitions ){
    this.transitions = new ArrayList<Transition>(transitions);
  }
  
  public void addTransition( Transition transition ){
    transitions.add( transition );
  }
  
  public Transition getNext(){
    if( position < transitions.size() ){
      ++ position;
      return transitions.get( position - 1 );
    } else {
      return null;
    }
  }
  
  public Transition getLast(){
    if( position > 0 ){
      -- position;
      return transitions.get( position );
    } else {
      return null;
    }
  }

  public int getPosition(){
    return position;
  }
  
  public int getSize(){
    return transitions.size();
  }

  public Transition seeNext(){
    if( position < transitions.size() ){
      return transitions.get( position );
    } else {
      return null;
    }
  }
}
