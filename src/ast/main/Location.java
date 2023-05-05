package src.ast.main;

public class Location {
    public int line;
    public int column;

    public Location(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public boolean Compare(Location other) {
        return line == other.line && column == other.column;
    } 
}