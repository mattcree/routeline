package models.routefinder;

import models.StationStop;
import models.StopConnection;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cree on 25/03/2017.
 */
public class Network {

    private Collection<StationStop> stops;
    private Collection<StopConnection> connections;


    public Network() {
        this.stops = new LinkedList<>();
        this.connections = new LinkedList<>();
    }

    //Accessor Methods
    //Collections
    /**
     * Gets the Collection of StationStops
     * @return A Collection of StationStop
     */
    public Collection<StationStop> getAllStationStops() {
        return stops;
    }

    //Takes a station name and returns Collection
    //of StationStops at that Station
    public Collection<StationStop> getAllLineStationStopsAt(String name) {
        LinkedList<StationStop> list = new LinkedList<>();
        for(StationStop stop : stops) {
            if(stop.getName().equals(name)) list.add(stop);
        }
        return list;
    }

    public Collection<String> getAllLines() {
        LinkedList<String> list = new LinkedList<>();
        for(StationStop stop : stops) {
            String line = stop.getLine();
            if(!list.contains(line)) list.add(line);
        }
        return list;
    }

    public Collection<String> getAllStationStopNames() {
        LinkedList<String> list = new LinkedList<>();
        for(StationStop stop : stops) {
            String stopName = stop.getName();
            if(!list.contains(stopName)) list.add(stopName);
        }
        return list;
    }

    public Collection<StationStop> getConnectedStationStops(StationStop stop) {
        LinkedList<StationStop> stops = new LinkedList<>();
        for(StopConnection connection : connections) {
            if (connection.from().equals(stop)) {
                stops.add(connection.to());
            }
        }
        return stops;
    }

    /**
     * Gets the Collection of StopConnections.
     * @return A Collection of Path
     */
    public Collection<StopConnection> getAllStopConnections() {
        return connections;
    }

    //Get StationStops and StopConnections
    /**
     * Gets a stop from the List of StationStops based on the name and line params
     * @param name The name of a StationStop
     * @param line The line the StationStop is on
     * @return A StationStop object if found, or null if no StationStop found
     */
    public StationStop getStationStop(String name, String line) {
        for (StationStop stop : stops) {
            if (stop.getName().equals(name) && stop.getLine().equals(line)) {
                return stop;
            }
        }
        return null;
    }

    public StopConnection getOneWayStopConnection(StationStop one, StationStop two) {
        for(StopConnection connection : connections) {
            if (isConnected(one, two, connection)) return connection;
        }
        return null;
    }

    //StationStop Methods
    //Adding StationStops
    /**
     * Adds a new stop to the StationStops list and returns true. If the
     * StationStop already exists, returns false.
     * @param name The name of a StationStop
     * @param line The line of a StationStop
     * @return True if the StationStop has been added to the list, false if it already exists.
     */
    public boolean addStationStop(String name, String line) {
        if (getStationStop(name, line) != null) return false;
        StationStop stop = new StationStop(name, line);
        return stops.add(stop);
    }

    //Removing StationStops
    /**
     * Removes a StationStop from the list of stops. Also removes any StopConnections
     * made to this StationStop.
     * @param name The name of a StationStop
     * @param line The line of a StationStop
     * @return True if the StationStop has been successfully removed, false if
     * the stop does not exist.
     */
    public boolean removeStationStop(String name, String line) {
        StationStop stopToRemove = getStationStop(name, line);
        if (stopToRemove == null) return false;
        removeAllStopConnections(stopToRemove);
        for(StationStop stop : stops) {
            if (stop.equals(stopToRemove)) return stops.remove(stopToRemove);
        }
        return false;
    }

    //Path methods
    //Adding StopConnections
    /**
     * Adds a connection using String values to identify the StationStops to be
     * connected. A complete Path is bi-directional, and constitutes
     * two separate unidirectional connections.
     * @param name1 First StationStop's name
     * @param line1 First StationStop's line
     * @param name2 Second StationStop's name
     * @param line2 Second StationStop's line
     * @param distance The time between the StationStops
     * @return True if the Path was successfully added, False if either StationStop doesn't
     * exist or the time is less than 1.
     */
    public boolean addStopConnection(String name1, String line1, String name2, String line2, int distance) {
        StationStop one = getStationStop(name1, line1);
        StationStop two = getStationStop(name2, line2);
        if (one == null || two == null) return false;
        return addStopConnection(one, two, distance);
    }

    /**
     * Adds a connection using StationStop objects. A complete Path
     * is bi-directional, and constitutes two separate unidirectional
     * connections.
     * @param one The first StationStop
     * @param two The second StationStop
     * @param distance The time between the StationStops
     * @return True if the Path was successfully added, False if either StationStop doesn't
     * exist or the time is less than 1.
     */
    public boolean addStopConnection(StationStop one, StationStop two, int distance) {
        if (!stops.contains(one) || !stops.contains(two) || distance <= 0) return false;
        return addOneWayStopConnection(one, two, distance) && addOneWayStopConnection(two, one, distance);
    }

    //Removing StopConnections
    /**
     * Removes Path(s) from Path Collection.
     * @param one StationStop one
     * @param two StationStop two
     * @return True if the connection has been successfully removed, false
     * if connection does not exist.
     */
    public boolean removeStopConnectionBetween(StationStop one, StationStop two) {
        int removalCount = 0;
        if (!stops.contains(one) || !stops.contains(two)) return false;
        Iterator<StopConnection> iterator = connections.iterator();
        while (iterator.hasNext()) {
            StopConnection connection = iterator.next();
            if (isAStopConnectionBetween(one, two, connection)) {
                iterator.remove();
                removalCount++;
            }
        }
        return removalCount == 2;
    }

    public boolean removeStopConnectionBetween(String name1, String line1, String name2, String line2) {
        StationStop one = getStationStop(name1, line1);
        StationStop two = getStationStop(name2, line2);
        if (one == null || two == null) return false;
        return removeStopConnectionBetween(one, two);
    }


    public boolean removeAllStopConnections(StationStop stop) {
        Collection<StationStop> stops = getConnectedStationStops(stop);
        if (stops.isEmpty()) return false;
        for(StationStop targetStationStop : stops) {
            removeStopConnectionBetween(stop, targetStationStop);
        }
        return true;
    }

    //Private helper functions
    //Adds a Path A -> B
    private boolean addOneWayStopConnection(StationStop a, StationStop b, int time) {
        StopConnection connection = new StopConnection(a, b, time);
        return connections.add(connection);
    }

    //True if Path represents Path A -> B
    private boolean isConnected(StationStop a, StationStop b, StopConnection connection) {
        return connection.from().equals(a) && connection.to().equals(b);
    }

    //True if Path is A -> B or B -> A
    private boolean isAStopConnectionBetween(StationStop a, StationStop b, StopConnection connection) {
        return isConnected(a, b, connection) || isConnected(b, a, connection);
    }

}
