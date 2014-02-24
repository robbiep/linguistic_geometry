package repl;

public class CommandTuple {
  public final String   name;
  public final String   description;
  public final Command  command;
  
  public CommandTuple( String name, String description, Command command ) {
    super();
    this.name = name;
    this.description = description;
    this.command = command;
  }
 
}
