package src.ast.expressions;

import src.ast.main.Type;
import src.ast.main.Location;

public class Expression { 
    protected Type type;  
    protected Location loc;

    public Expression(int line, int column){
        this.loc = new Location(line, column);
        //constructor
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }    

}