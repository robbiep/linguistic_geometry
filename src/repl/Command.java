package repl;

import java.util.ArrayList;

import lg.AbstractBoardGame;

public interface Command {
  public void execute( AbstractBoardGame abg, ArrayList<String> args );
}
