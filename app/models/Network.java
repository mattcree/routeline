package models;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cree on 25/03/2017.
 */
public class Network {

    private Collection<Stop> stops;
    private Collection<Connection> connections;


    public Network() {
        this.stops = new LinkedList<>();
        this.connections = new LinkedList<>();
    }

    //Accessor Methods
    //Collections
    /**
     * Gets the Collection of Stops
     * @return A Collection of Stop
     */
    public Collection<Stop> getAllStops() {
        return stops;
    }

    //Takes a station name and returns Collection
    //of Stops at that Station
    public Collection<Stop> getAllLineStopsAt(String name) {
        LinkedList<Stop> list = new LinkedList<>();
        for(Stop stop : stops) {
            if(stop.getName().equals(name)) list.add(stop);
        }
        return list;
    }

    public Collection<String> getAllLines() {
        LinkedList<String> list = new LinkedList<>();
        for(Stop stop : stops) {
            String line = stop.getLine();
            if(!list.contains(line)) list.add(line);
        }
        return list;
    }

    public Collection<String> getAllStopNames() {
        LinkedList<String> list = new LinkedList<>();
        for(Stop stop : stops) {
            String stopName = stop.getName();
            if(!list.contains(stopName)) list.add(stopName);
        }
        return list;
    }

    public Collection<Stop> getConnectedStops(Stop stop) {
        LinkedList<Stop> stops = new LinkedList<>();
        for(Connection connection : connections) {
            if (connection.from().equals(stop)) {
                stops.add(connection.to());
            }
        }
        return stops;
    }

    /**
     * Gets the Collection of Connections.
     * @return A Collection of Connection
     */
    public Collection<Connection> getAllConnections() {
        return connections;
    }

    //Get Stops and Connections
    /**
     * Gets a stop from the List of Stops based on the name and line params
     * @param name The name of a Stop
     * @param line The line the Stop is on
     * @return A Stop object if found, or null if no Stop found
     */
    public Stop getStop(String name, String line) {
        for (Stop stop : stops) {
            if (stop.getName().equals(name) && stop.getLine().equals(line)) {
                return stop;
            }
        }
        return null;
    }

    public Connection getOneWayConnection(Stop one, Stop two) {
        for(Connection connection : connections) {
            if (isConnected(one, two, connection)) return connection;
        }
        return null;
    }

    //Stop Methods
    //Adding Stops
    /**
     * Adds a new stop to the Stops list and returns true. If the
     * Stop already exists, returns false.
     * @param name The name of a Stop
     * @param line The line of a Stop
     * @return True if the Stop has been added to the list, false if it already exists.
     */
    public boolean addStop(String name, String line) {
        if (getStop(name, line) != null) return false;
        return stops.add(new Stop(name, line));
    }

    //Removing Stops
    /**
     * Removes a Stop from the list of stops. Also removes any Connections
     * made to this Stop.
     * @param name The name of a Stop
     * @param line The line of a Stop
     * @return True if the Stop has been successfully removed, false if
     * the stop does not exist.
     */
    public boolean removeStop(String name, String line) {
        Stop stopToRemove = getStop(name, line);
        if (stopToRemove == null) return false;
        removeAllConnections(stopToRemove);
        for(Stop stop : stops) {
            if (stop.equals(stopToRemove)) return stops.remove(stopToRemove);
        }
        return false;
    }

    //Connection methods
    //Adding Connections
    /**
     * Adds a connection using String values to identify the Stops to be
     * connected. A complete Connection is bi-directional, and constitutes
     * two separate unidirectional connections.
     * @param name1 First Stop's name
     * @param line1 First Stop's line
     * @param name2 Second Stop's name
     * @param line2 Second Stop's line
     * @param distance The distance between the Stops
     * @return True if the Connection was successfully added, False if either Stop doesn't
     * exist or the distance is less than 1.
     */
    public boolean addConnection(String name1, String line1, String name2, String line2, int distance) {
        Stop one = getStop(name1, line1);
        Stop two = getStop(name2, line2);
        if (one == null || two == null) return false;
        return addConnection(one, two, distance);
    }

    /**
     * Adds a connection using Stop objects. A complete Connection
     * is bi-directional, and constitutes two separate unidirectional
     * connections.
     * @param one The first Stop
     * @param two The second Stop
     * @param distance The distance between the Stops
     * @return True if the Connection was successfully added, False if either Stop doesn't
     * exist or the distance is less than 1.
     */
    public boolean addConnection(Stop one, Stop two, int distance) {
        if (!stops.contains(one) || !stops.contains(two) || distance <= 0) return false;
        return addOneWayConnection(one, two, distance) && addOneWayConnection(two, one, distance);
    }

    //Removing Connections
    /**
     * Removes Connection(s) from Connection Collection.
     * @param one Stop one
     * @param two Stop two
     * @return True if the connection has been successfully removed, false
     * if connection does not exist.
     */
    public boolean removeConnectionBetween(Stop one, Stop two) {
        int removalCount = 0;
        if (!stops.contains(one) || !stops.contains(two)) return false;
        Iterator<Connection> iterator = connections.iterator();
        while (iterator.hasNext()) {
            Connection connection = iterator.next();
            if (isAConnectionBetween(one, two, connection)) {
                iterator.remove();
                removalCount++;
            }
        }
        return removalCount == 2;
    }

    public boolean removeConnectionBetween(String name1, String line1, String name2, String line2) {
        Stop one = getStop(name1, line1);
        Stop two = getStop(name2, line2);
        if (one == null || two == null) return false;
        return removeConnectionBetween(one, two);
    }


    public boolean removeAllConnections(Stop stop) {
        Collection<Stop> stops = getConnectedStops(stop);
        if (stops.isEmpty()) return false;
        for(Stop targetStop : stops) {
            removeConnectionBetween(stop, targetStop);
        }
        return true;
    }

    //Private helper functions
    //Adds a Connection A -> B
    private boolean addOneWayConnection(Stop a, Stop b, int distance) {
        return connections.add(new Connection(a, b, distance));
    }

    //True if Connection represents Connection A -> B
    private boolean isConnected(Stop a, Stop b, Connection connection) {
        return connection.from().equals(a) && connection.to().equals(b);
    }

    //True if Connection is A -> B or B -> A
    private boolean isAConnectionBetween(Stop a, Stop b, Connection connection) {
        return isConnected(a, b, connection) || isConnected(b, a, connection);
    }

}
