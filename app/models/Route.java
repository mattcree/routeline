package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cree on 30/04/2017.
 */
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Constraints.Required
    public StationStop start;

    @Constraints.Required
    public StationStop destination;

    @OneToMany
    @Constraints.Required
    public List<StationStop> route;

    @OneToMany
    @Constraints.Required
    public List<StopConnection> connections;

    public int timeInMinutes;
    public int numberOfChanges;

    public Route(LinkedList<StationStop> route, LinkedList<StopConnection> connections) {
        this.start = route.getFirst();
        this.destination = route.getLast();
        this.route = route;
        this.connections = connections;
        this.timeInMinutes = timeForRoute(route, connections);
        this.numberOfChanges = numberOfChanges(connections);
    }


    private int timeForRoute(LinkedList<StationStop> stops, LinkedList<StopConnection> connections){
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

    private int getTime(StationStop here, StationStop there, LinkedList<StopConnection> connections) {
        for (StopConnection connection : connections){
            if ((connection.from().equals(here)) && (connection.to().equals(there)))
                return connection.time();
        }
        throw new RuntimeException("No connection between these stops.");
    }

    private int numberOfChanges(LinkedList<StopConnection> connections) {
        int number = 0;
        for (StopConnection connection : connections) {
            if(connection.isLineChange()) number++;
        }
        return number;
    }

}
