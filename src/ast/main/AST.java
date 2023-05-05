package src.ast.main;

import java.util.ArrayList;
import src.ast.statements.Statement;
import src.ast.main.Type;

public class AST {
    private String name;
    private Location loc;
    private Statement stmt;
    private ArrayList<AST> childs;
    private AST parent;
    public boolean visited;
    public String error;

    public AST(String name, int line, int column, Statement stmt) {
        this.name = name;
        this.loc = new Location(line, column);
        this.stmt = stmt;
        childs = new ArrayList<AST>();
    }    

    public AST(String name, int line, int column, Statement stmt, AST parent) {
        this.name = name;
        this.loc = new Location(line, column);
        this.stmt = stmt;
        this.parent = parent;   
        childs = new ArrayList<AST>();
    }    

    public AST(String name) {
        this.name = name;
        childs = new ArrayList<AST>();
    }

    

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return loc;
    }

    public void addChild(AST node) {
        childs.add(node);
    }

    public AST getChild(int index) {
        return childs.get(index);
    }

    public ArrayList<AST> getChilds() {
        return childs;
    }

    public Statement getStatement() {
        return stmt;
    }

    public AST getParent() {
        return parent;
    }

    public void setParent(AST parent) {
        this.parent = parent;
    }


}