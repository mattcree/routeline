package models.routefinder;

/**
 * Created by Cree on 15/04/2017.
 */
public class Connection {

    private String name;
    private Stop from;
    private Stop to;
    private int distance;

    public Connection(Stop from, Stop to, int distance) {
        this.name = from.getName() + " to " + to.getName();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Stop from() {
        return from;
    }

    public Stop to() {
        return to;
    }

    public int distance() {
        return distance;
    }

    public boolean isLineChange() {
        return !from.getLine().equals(to.getLine());
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }

}
