package models.routefinder;
import models.Route;
import models.StationStop;
import models.StopConnection;

import java.util.*;

public class Routefinder {

    private Collection<StationStop> stops;
    private Collection<StopConnection> connections;
    private Set<StationStop> calculatedStationStops;
    private Set<StationStop> remainingStationStops;
    private Map<StationStop, StationStop> predecessors;
    private Map<StationStop, Integer> knownTimes;

    public Routefinder() {
        this.stops = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public Routefinder(Collection<StationStop> stops, Collection<StopConnection> connections) {
        this.stops = new ArrayList<>(stops);
        this.connections = new ArrayList<>(connections);
    }

    public Routefinder(Network network) {
        this.stops = new ArrayList<>(network.getAllStationStops());
        this.connections = new ArrayList<>(network.getAllStopConnections());
    }

    public Collection<StationStop> getStops() {
        return new ArrayList<>(stops);
    }

    public Collection<StopConnection> getConnections() {
        return new ArrayList<>(connections);
    }

    public void setConnections(Collection<StopConnection> connections) {
        this.connections = connections;
    }

    public void setStops(Collection<StationStop> stops) {
        this.stops = stops;
    }


    //Main function which populates
    public void generateTimesFrom(StationStop originStationStop) {
        calculatedStationStops = new HashSet<>();
        remainingStationStops = new HashSet<>();
        knownTimes = new HashMap<>();
        //StationStop as Key and Value the StationStop which preceeds it on its shortest route
        predecessors = new HashMap<>();

        //Adds our start point to knownTimes map with time of 0
        knownTimes.put(originStationStop, 0);
        //Adds starting stop to the list of stations
        //whose shortest routes have not been calculated
        remainingStationStops.add(originStationStop);

        //While there are still stops whose routes have not been calculated
        while (remainingStationStops.size() > 0) {
            //Gets the closest stop which is still to be calculated
            //(starts with the source)
            StationStop stop = getNearestStationStop(remainingStationStops);
            //Adds that stop to the calculatedStationStops
            calculatedStationStops.add(stop);
            //removes it from the stops still to be calculated
            remainingStationStops.remove(stop);
            //
            findClosest(stop);
        }
    }

    //Takes a Set of stops
    private StationStop getNearestStationStop(Set<StationStop> stops) {
        //Initialises the minimum as null
        StationStop minimum = null;
        //iterates through each stop in the Set
        for (StationStop stop : stops) {
            //Assigns current stop as new minimum if the minimum is currently null
            if (minimum == null) {
                minimum = stop;
            //if the stop has a shorter known time
            //that the shortest known time of the current minimum
            //update the minimum to be the stop
            } else {
                if (shortestKnownTime(stop) < shortestKnownTime(minimum)) {
                    minimum = stop;
                }
            }
        }
        return minimum;
    }

    //Takes a destination stop
    private int shortestKnownTime(StationStop destination) {
        //gets the time value from the knownTimes list
        Integer time = this.knownTimes.get(destination);

        //if the destination is not in the knownTimes list
        //MAX_VALUE used in place of INFINITY
        if(time == null) return Integer.MAX_VALUE;
        else return time;
    }

    //Finds the closest stations to the start point and
    private void findClosest(StationStop stopOnRoute) {
        //Gets the list of stops connected to the starting stop
        List<StationStop> connectedStationStops = getConnectedStationStops(stopOnRoute);
        //Iterates through each connected stop
        for (StationStop stop : connectedStationStops) {
            //if the shortest known time to the current stop is greater
            //than the shortest known time from the current stop plus
            int shortestKnownPath = shortestKnownTime(stop);
            int timeToNext = getTime(stopOnRoute, stop);
            //i.e. if no path known shortestKnownPath returns huge value, making
            //this true
            if (shortestKnownPath + timeToNext < shortestKnownPath) {
                knownTimes.put(stop, shortestKnownPath + timeToNext);
                    predecessors.put(stop, stopOnRoute);
                remainingStationStops.add(stop);
            }
        }
    }

    //Returns the time of the connection between here and there
    //from the connection list.
    private int getTime(StationStop here, StationStop there) {
        for (StopConnection connection : connections) {
            if ((connection.from().equals(here)) && (connection.to().equals(there)))
                return connection.time();
        }
        throw new RuntimeException("No connection between these stops.");
    }

    //Returns a List of stations connected to the chosen stop
    private List<StationStop> getConnectedStationStops(StationStop stop) {
        List<StationStop> connectedStationStops = new ArrayList<>();
        //Goes through each connection in our list of connections
        for (StopConnection connection : connections) {
            //checks if the connection's origin is the param stop *and* that
            //the connection's destination has not already been calculated.

            //If both are true, adds the destination to the list of connectedStationStops
            if ((connection.from().equals(stop)) && !calculatedStationStops.contains(connection.to())) {
                connectedStationStops.add(connection.to());
            }
        }
        //Returns the list
        return connectedStationStops;
    }

    public LinkedList<StationStop> getRouteTo(StationStop destination) {
        LinkedList<StationStop> route = new LinkedList<>();
        StationStop waypoint = destination;
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

//    public Route getRouteToAsRoute(StationStop destination) {
//        return getRouteTo()
//    }

    private StopConnection getConnection(StationStop here, StationStop there) {
        for (StopConnection connection : connections) {
            if ((connection.from().equals(here)) && (connection.to().equals(there)))
                return connection;
        }
        throw new RuntimeException("No connection between these stops.");
    }


    public LinkedList<StopConnection> getAllConnectionsOnRoute(List<StationStop> route){
        LinkedList<StopConnection> routeConnections = new LinkedList<>();
        StationStop thisStop = null;
        StationStop thatStop = null;
        for (StationStop stop : route) {
            if (thisStop == null) thisStop = stop;
            else {
                thatStop = stop;
                routeConnections.add(getConnection(thisStop,thatStop));
                thisStop = thatStop;
            }
        }
        return routeConnections;
    }

    public static Route getBestRoute(List<StationStop> starts, List<StationStop> destinations, Routefinder rf) {
        Route shortestRoute = null;
        for(StationStop startingStop : starts) {
            rf.generateTimesFrom(startingStop);
            for(StationStop destinationStop : destinations) {
                LinkedList<StationStop> stops = rf.getRouteTo(destinationStop);
                LinkedList<StopConnection> connections = rf.getAllConnectionsOnRoute(stops);
                Route currentRoute = new Route(stops, connections);
                if (shortestRoute == null) shortestRoute = currentRoute;
                if (shortestRoute.timeInMinutes > currentRoute.timeInMinutes) shortestRoute = currentRoute;
            }
        }
        return shortestRoute;
    }

}



