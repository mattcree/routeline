package models.routefinder;
import models.StationStop;
import models.StopConnection;

import java.util.*;

public class Routefinder {

    private Collection<StationStop> stops;
    private Collection<StopConnection> connections;
    private Set<StationStop> calculatedStationStops;
    private Set<StationStop> remainingStationStops;
    private Map<StationStop, StationStop> predecessors;
    private Map<StationStop, Integer> knownDistances;

    public Routefinder() {
        this.stops = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public Routefinder(Collection<StationStop> stops, Collection<StopConnection> connections) {
        this.stops = new ArrayList<>(stops);
        this.connections = new ArrayList<>(connections);
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
    public void generateDistancesFrom(StationStop originStationStop) {
        calculatedStationStops = new HashSet<>();
        remainingStationStops = new HashSet<>();
        knownDistances = new HashMap<>();
        //StationStop as Key and Value the StationStop which preceeds it on its shortest route
        predecessors = new HashMap<>();

        //Adds our start point to knownDistances map with time of 0
        knownDistances.put(originStationStop, 0);
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
                if (shortestKnownDistance(stop) < shortestKnownDistance(minimum)) {
                    minimum = stop;
                }
            }
        }
        return minimum;
    }

    //Takes a destination stop
    private int shortestKnownDistance(StationStop destination) {
        //gets the time value from the knownDistances list
        Integer time = this.knownDistances.get(destination);

        //if the destination is not in the knownDistances list
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
            int shortestKnownPath = shortestKnownDistance(stop);
            System.out.println(stopOnRoute);
            System.out.println(stop);
            int timeToNext = getDistance(stopOnRoute, stop);
            System.out.println(timeToNext);

            //i.e. if no path known shortestKnownPath returns huge value, making
            //this true
            if (shortestKnownPath + timeToNext < shortestKnownPath) {
                knownDistances.put(stop, shortestKnownPath + timeToNext);
                    predecessors.put(stop, stopOnRoute);
                remainingStationStops.add(stop);
            }
        }
    }

    //Returns the time of the connection between here and there
    //from the connection list.
    private int getDistance(StationStop here, StationStop there) {
        StopConnection connection1 = StopConnection.find.where()
                .eq("stop_a_id", here.id)
                .eq("stop_b_id", there.id)
                .findUnique();
        return connection1.time();
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

    public Collection<StationStop> getRouteTo(StationStop destination) {
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



}



