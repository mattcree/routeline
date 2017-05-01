package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cree on 30/04/2017.
 */

public class Route {


    public StationStop start;
    public StationStop destination;
    public List<StationStop> route;
    public int timeInMinutes;
    public int numberOfChanges;
    public List<StopConnection> changes;
    public List<StopConnection> connections;


    public Route(LinkedList<StationStop> route, LinkedList<StopConnection> connections) {
        this.start = route.getFirst();
        this.destination = route.getLast();
        this.route = route;
        this.timeInMinutes = timeForRoute(route, connections);
        this.changes = changeList(connections);
        this.numberOfChanges = changes.size();
        this.connections = connections;
    }


    private int timeForRoute(List<StationStop> stops, List<StopConnection> connections){
        if (timeInMinutes > 0) return timeInMinutes;
        int time = 0;
        if (route == null) return time;
        StationStop thisStop = null;
        StationStop thatStop = null;
        for (StationStop stop : stops) {
            if (thisStop == null) thisStop = stop;
            else {
                thatStop = stop;
                time = time + getTime(thisStop, thatStop, connections);
                thisStop = thatStop;
            }
        }
        timeInMinutes = time;
        return time;
    }

    private int getTime(StationStop here, StationStop there, List<StopConnection> connections) {
        for (StopConnection connection : connections){
            if ((connection.from().equals(here)) && (connection.to().equals(there)))
                return connection.time();
        }
        throw new RuntimeException("No connection between these stops.");
    }

    private int numberOfChanges(List<StopConnection> connections) {
        int number = 0;
        for (StopConnection connection : connections) {
            if(connection.isLineChange()) number++;
        }
        return number;
    }

    private List<StopConnection> changeList(List<StopConnection> connections) {
        List<StopConnection> changes = new LinkedList<>();
        for (StopConnection connection : connections) {
            if(connection.isLineChange()) changes.add(connection);
        }
        return changes;
    }
}
