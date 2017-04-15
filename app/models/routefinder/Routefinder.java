package models.routefinder;
import models.StopConnection;
import models.StationStop;

import java.util.*;

public class Routefinder {

    private final Collection<Stop> stops;
    private final Collection<Connection> connections;
    private final Network network;

    private Set<Stop> calculatedStops;
    private Set<Stop> remainingStops;
    private Map<Stop, Stop> predecessors;
    private Map<Stop, Integer> knownDistances;

    public Routefinder(Network network) {
        this.network = network;
        this.stops = new ArrayList<>(network.getAllStops());
        this.connections = new ArrayList<>(network.getAllConnections());
    }

    public Collection<Stop> getCalculatedStops() {
        return this.calculatedStops;
    }
    public Collection<Connection> getConnections() {
        return this.connections;
    }

    //Main function which populates
    public void generateDistancesFrom(Stop originStop) {
        calculatedStops = new HashSet<>();
        remainingStops = new HashSet<>();
        knownDistances = new HashMap<>();
        //Stop as Key and Value the Stop which preceeds it on its shortest route
        predecessors = new HashMap<>();

        //Adds our start point to knownDistances map with distance of 0
        knownDistances.put(originStop, 0);
        //Adds starting stop to the list of stations
        //whose shortest routes have not been calculated
        remainingStops.add(originStop);

        //While there are still stops whose routes have not been calculated
        while (remainingStops.size() > 0) {
            //Gets the closest stop which is still to be calculated
            //(starts with the source)
            Stop stop = getNearestStop(remainingStops);
            //Adds that stop to the calculatedStops
            calculatedStops.add(stop);
            //removes it from the stops still to be calculated
            remainingStops.remove(stop);
            //
            findClosest(stop);
        }
    }

    //Takes a Set of stops
    private Stop getNearestStop(Set<Stop> stops) {
        //Initialises the minimum as null
        Stop minimum = null;
        //iterates through each stop in the Set
        for (Stop stop : stops) {
            //Assigns current stop as new minimum if the minimum is currently null
            if (minimum == null) {
                minimum = stop;
            //if the stop has a shorter known distance
            //that the shortest known distance of the current minimum
            //update the minimum to be the stop
            } else {
                if (shortestKnownDistance(stop) < shortestKnownDistance(minimum)) {
                    minimum = stop;
                }
            }
        }
        return minimum;
    }

    //Takes a destination stop
    private int shortestKnownDistance(Stop destination) {
        //gets the distance value from the knownDistances list
        Integer distance = this.knownDistances.get(destination);

        //if the destination is not in the knownDistances list
        //MAX_VALUE used in place of INFINITY
        if(distance == null) return Integer.MAX_VALUE;
        else return distance;
    }

    //Finds the closest stations to the start point and
    private void findClosest(Stop stopOnRoute) {
        //Gets the list of stops connected to the starting stop
        List<Stop> connectedStops = getConnectedStops(stopOnRoute);
        //Iterates through each connected stop
        for (Stop stop : connectedStops) {
            //if the shortest known distance to the current stop is greater
            //than the shortest known distance from the current stop plus
            int shortestKnownPath = shortestKnownDistance(stop);
            int distanceToNext = getDistance(stopOnRoute, stop);

            //i.e. if no path known shortestKnownPath returns huge value, making
            //this true
            if (shortestKnownPath + distanceToNext < shortestKnownPath) {
                knownDistances.put(stop, shortestKnownPath + distanceToNext);
                    predecessors.put(stop, stopOnRoute);
                remainingStops.add(stop);
            }
        }
    }

    //Returns the distance of the connection between here and there
    //from the connection list.
    private int getDistance(Stop here, Stop there) {
        for (Connection connection : connections)
            if ((connection.from() == here) && (connection.to() == there)) return connection.distance();
        throw new RuntimeException("No connection between these stops.");
    }

    //Returns a List of stations connected to the chosen stop
    private List<Stop> getConnectedStops(Stop stop) {
        List<Stop> connectedStops = new ArrayList<>();
        //Goes through each connection in our list of connections
        for (Connection connection : connections) {
            //checks if the connection's origin is the param stop *and* that
            //the connection's destination has not already been calculated.

            //If both are true, adds the destination to the list of connectedStops
            if ((connection.from().equals(stop)) && !calculatedStops.contains(connection.to())) {
                connectedStops.add(connection.to());
            }
        }
        //Returns the list
        return connectedStops;
    }

    public Collection<Stop> getRouteTo(Stop destination) {
        LinkedList<Stop> route = new LinkedList<>();
        Stop waypoint = destination;
        // check if destination has a preceeding stop
        //listed. If no stop preceeds destination,
        //no route was found and returns null.
        if (predecessors.get(waypoint) == null) {
            return null;
        }
        //Our initial step is added to our route
        route.add(waypoint);
        //While there are still preceeding steps found
        //we remove them from our predecessors map
        //and add them to our route
        while (predecessors.get(waypoint) != null) {
            waypoint = predecessors.get(waypoint);
            route.add(waypoint);
        }
        //we reverse the route to get an ordered list of stops
        Collections.reverse(route);

        //and return the route
        return route;
    }

    public int timeForRoute(Stop destination){
        int time = 0;
        Collection<Stop> route = getRouteTo(destination);
        if (route == null) return time;
        System.out.println(route);

        Stop thisStop = null;
        Stop thatStop = null;
        for (Stop stop : route) {
            if (thisStop == null) thisStop = stop;
            else {
                thatStop = stop;
                Connection connection = network.getOneWayConnection(thisStop, thatStop);
                time = time + connection.distance();
                System.out.println(connection.distance());
                thisStop = thatStop;
            }
        }


        return time;
    }



}



