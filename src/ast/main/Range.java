package src.ast.main;

import src.ast.main.Location;


public class Range {
    private Location begin;
    private Location end;

    public Range(Location begin, Location end) {
        this.begin = begin;
        this.end = end;
    }    

    public Location getBegin() {
        return begin;
    }

    public Location getEnd() {
        return end;
    }
}