package routefindertest;

import models.routefinder.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Cree on 01/04/2017.
 */
public class NetworkTest {

    @Test
    public void createValidNetworkShouldNotBeNull() {
        Network network = new Network();
        Assert.assertNotNull(network);
    }

    //Getter Tests
    @Test
    public void getStopsShouldReturnEmptyListAfterInitialisation(){
        Network network = new Network();
        Assert.assertTrue(network.getAllStops().isEmpty());
    }

    @Test
    public void getConnectionsShouldReturnEmptyCollectionOfConnectionWhenNoConnection() {
        Network network = new Network();
        Assert.assertTrue(network.getAllConnections().isEmpty());
    }

    @Test
    public void getOneWayConnectionShouldReturnUnidirectionalConnection() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addConnection(name1, line1, name2, line1, 6);
        Stop secondStop = network.getStop(name2, line1);
        Stop firstStop = network.getStop(name1, line1);
        Connection connection = network.getOneWayConnection(secondStop, firstStop);
        Assert.assertTrue(connection.from().equals(secondStop) && connection.to().equals(firstStop));
    }

    @Test
    public void getConnectedStopsShouldReturnAllStopsConnectedToStop() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        String name3 = "Darlington";
        network.addStop(name3, line1);

        network.addConnection(name1, line1, name2, line1, 6);
        network.addConnection(name1, line1, name3, line1, 10);

        Stop one = network.getStop(name1, line1);
        Stop two = network.getStop(name2, line1);
        Stop three = network.getStop(name3, line1);

        Collection connections = new LinkedList<>();

        connections.add(two);
        connections.add(three);

        Assert.assertTrue(network.getConnectedStops(one).containsAll(connections));
    }

    @Test
    public void getAllLineStopsAtShouldReturnEmptyListForNoStationFound() {
        Network network = new Network();
        Assert.assertTrue(network.getAllLineStopsAt(name2).isEmpty());
    }

    @Test
    public void getAllLineStopsAtShouldReturnStationIfOnlyOneStation() {
        Network network = new Network();
        network.addStop(name1, line1);
        Stop stop = network.getStop(name1, line1);
        assert network.getAllLineStopsAt(name1).size() == 1;
        Assert.assertTrue(network.getAllLineStopsAt(name1).contains(stop));
    }

    @Test
    public void getAllLineStopsAtShouldOnlyReturnStopsAtStation() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line2);
        network.addStop(name1, line2);
        network.addStop(name1, line3);

        Stop stop = network.getStop(name2, line2);

        assert network.getAllLineStopsAt(name1).size() == 3;
        Assert.assertFalse(network.getAllLineStopsAt(name1).contains(stop));
    }

    @Test
    public void getAllLinesShouldReturnCollectionOfLines() {
        Network network = new Network();
        network.addStop(name1, line1);

        network.addStop(name1, line2);
        network.addStop(name1, line3);
        Assert.assertTrue(network.getAllLines().size() == 3);
        Assert.assertTrue(network.getAllLines().contains(line1));
        Assert.assertTrue(network.getAllLines().contains(line2));
        Assert.assertTrue(network.getAllLines().contains(line3));
    }

    //Stop Tests
    //Get Stop Tests
    @Test
    public void getStopShouldReturnNullIfNoStopExists(){
        Network network = new Network();
        network.addStop(name1, line1);
        Assert.assertNull(network.getStop(name2, line1));
    }

    @Test
    public void getStopShouldGetStop(){
        Network network = new Network();
        network.addStop(name1, line1);
        Stop stop = createValidStation();
        Assert.assertTrue(network.getStop(name1, line1).equals(stop));
    }

    //Add Stop Tests
    @Test
    public void addStopShouldReturnTrueAndAddStopToList(){
        Network network = new Network();
        Assert.assertTrue(network.addStop(name1, line1) && !network.getAllStops().isEmpty());
    }

    @Test
    public void addStopShouldReturnFalseIfNewStopAlreadyExists(){
        Network network = new Network();
        network.addStop(name1, line1);
        Assert.assertFalse(network.addStop(name1, line1));
    }

    //Remove Stop Tests
    @Test
    public void removeStopShouldRemoveStopFromList(){
        Network network = new Network();
        network.addStop(name1, line1);
        network.removeStop(name1, line1);
        Assert.assertTrue(network.getAllStops().isEmpty());
    }

    @Test
    public void removeStopShouldNotRemoveAnythingWhenNoStopFound(){
        Network network = new Network();
        network.addStop(name1, line1);
        network.removeStop(name2, name1);
        Assert.assertTrue(network.getAllStops().size() == 1);
    }

    @Test
    public void removeStopShouldAlsoRemoveAllConnectionsToStop() {
        Network network = new Network();
        network.addStop("York","A");
        network.addStop("Darlington","A");
        network.addStop("Newcastle","A");
        network.addStop("Berwick-upon-Tweed","A");
        network.addStop("Dunbar","A");
        network.addStop("Edinburgh Waverley","A");

        network.addConnection("York","A","Darlington","A",39);
        network.addConnection("York","A","Newcastle","A",28);
        network.addConnection("York","A","Berwick-upon-Tweed","A",28);
        network.addConnection("York","A","Dunbar","A",28);

        network.addConnection("Dunbar","A","Edinburgh Waverley","A",28);

        Stop stop = network.getStop("York", "A");

        assert network.getConnectedStops(stop).size() == 4;
        network.removeAllConnections(stop);
        assert network.getAllConnections().size() == 2;

        Assert.assertTrue(network.getConnectedStops(stop).isEmpty());
    }


    //Add Path Tests
    @Test
    public void addConnectionShouldReturnFalseIfStopsNotFound() {
        Network network = new Network();
        Stop stop1 = createValidStation();
        Stop stop2 = new Stop(name2, line2);
        Assert.assertFalse(network.addConnection(stop1, stop2, 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfFirstStopNotFound() {
        Network network = new Network();
        Stop stop1 = createValidStation();
        Stop stop2 = new Stop(name2, line2);
        network.addStop(stop2.getName(), stop2.getLine());
        Assert.assertFalse(network.addConnection(stop1, network.getStop(name2, line2), 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfSecondStopNotFound() {
        Network network = new Network();
        Stop stop1 = createValidStation();
        Stop stop2 = new Stop(name2, line2);
        network.addStop(stop2.getName(), stop2.getLine());
        Assert.assertFalse(network.addConnection(network.getStop(name2, line2), stop1, 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfBothStopsFoundButDistanceLessThanOne() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line2);
        Assert.assertFalse(network.addConnection(network.getStop(name1, line1), network.getStop(name2, line2), 0));
    }

    @Test
    public void addConnectionShouldReturnTrueIfBothStopsFoundAndDistanceGreaterThanZero() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line2);
        Assert.assertTrue(network.addConnection(network.getStop(name1, line1), network.getStop(name2, line2), 1));
    }

    @Test
    public void addConnectionShouldAddTwoConnectionsToConnectionList() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addConnection(network.getStop(name1, line1), network.getStop(name2, line1), 10);
        Assert.assertTrue(network.getAllConnections().size() == 2);
    }

    @Test
    public void addConnectionShouldReturnFalseIfConnectionAlreadyExists() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addConnection(network.getStop(name1, line1), network.getStop(name2, line1), 10);
        Assert.assertFalse(network.addConnection(name1, line1, name2, name1, 1));
    }

    //Remove Path Tests
    @Test
    public void removeConnectionShouldReturnFalseIfNoConnections() {
        Network network = new Network();
        Assert.assertFalse(network.removeConnectionBetween(from, to));
    }

    @Test
    public void removeConnectionShouldReturnFalseWhenStopsExistButNoConnection() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        Assert.assertFalse(network.removeConnectionBetween(network.getStop(name1, line1), network.getStop(name2, line1)));
    }

    @Test
    public void removeConnectionShouldReturnShouldRemoveConnection() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addConnection(name1, line1, name2, line1, 6);
        Assert.assertTrue(network.removeConnectionBetween(network.getStop(name1, line1), network.getStop(name2, line1)));
    }

    @Test
    public void removeConnectionShouldRemoveCorrectConnection() {
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addStop(name3, line1);

        network.addConnection(name1, line1, name2, line1, 6);
        network.addConnection(name2, line1, name3, line1, 10);

        Stop two = network.getStop(name2, line1);
        Stop three = network.getStop(name3, line1);

        assert network.getOneWayConnection(three, two) != null;
        network.removeConnectionBetween(three, two);

        Connection connection1 = network.getOneWayConnection(three, two);
        Connection connection2 = network.getOneWayConnection(two, three);

        Assert.assertTrue(connection1 == null && connection2 == null);
    }

    @Test
    public void removeConnectionShouldAllowStringParams(){
        Network network = new Network();
        network.addStop(name1, line1);
        network.addStop(name2, line1);
        network.addStop(name3, line1);

        Stop one = network.getStop(name1, line1);
        Stop two = network.getStop(name2, line1);
        Stop three = network.getStop(name3, line1);

        network.addConnection(name1, line1, name2, line1, 6);
        network.addConnection(name1, line1, name3, line1, 10);

        assert network.getConnectedStops(one).contains(three);

        network.removeConnectionBetween(name1, line1, name3, line1);

        Assert.assertFalse(network.getConnectedStops(one).contains(three));
    }

    //Private test helpers
    //Stop Creation
    private String name = "Newcastle";
    private String line = "A";
    private String lineToString = "Stop: Newcastle, Line: A";

    private Stop createValidStation() {
        return new Stop(name, line1);
    }

    //Path Creation
    private int cost = 5;
    private String line1 = "A";
    private String line2 = "B";
    private String line3 = "C";
    private String name1 = "Newcastle";
    private String name2 = "Morpeth";
    private String name3 = "Darlington";
    private Stop from = new Stop(name1, line1);
    private Stop to = new Stop(name2, line1);

    private Connection createValidConnection() {
        return new Connection(from, to, cost);
    }

}
