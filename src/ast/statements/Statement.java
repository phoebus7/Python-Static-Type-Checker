package src.ast.statements;

import src.ast.main.Location;
import src.ast.main.Type;


public abstract class Statement{
    private Location loc;
    public String error;
    public String file;

    public Statement(int line, int column) {
        loc = new Location(line, column);
        //TODO Auto-generated constructor stub
    }

    public Location getLoc() {
        return loc;
    }

    public abstract Type getType() ;
   // public abstract Statement getStatement();
   // 
    
}
