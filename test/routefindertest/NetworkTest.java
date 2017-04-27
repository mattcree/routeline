package routefindertest;

import models.StationStop;
import models.StopConnection;
import models.routefinder.Network;
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
        Assert.assertTrue(network.getAllStationStops().isEmpty());
    }

    @Test
    public void getConnectionsShouldReturnEmptyCollectionOfConnectionWhenNoConnection() {
        Network network = new Network();
        Assert.assertTrue(network.getAllStopConnections().isEmpty());
    }

    @Test
    public void getOneWayConnectionShouldReturnUnidirectionalConnection() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStopConnection(name1, line1, name2, line1, 6);
        StationStop secondStop = network.getStationStop(name2, line1);
        StationStop firstStop = network.getStationStop(name1, line1);
        StopConnection connection = network.getOneWayStopConnection(secondStop, firstStop);
        Assert.assertTrue(connection.from().equals(secondStop) && connection.to().equals(firstStop));
    }

    @Test
    public void getConnectedStopsShouldReturnAllStopsConnectedToStop() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        String name3 = "Darlington";
        network.addStationStop(name3, line1);

        network.addStopConnection(name1, line1, name2, line1, 6);
        network.addStopConnection(name1, line1, name3, line1, 10);

        StationStop one = network.getStationStop(name1, line1);
        StationStop two = network.getStationStop(name2, line1);
        StationStop three = network.getStationStop(name3, line1);

        Collection connections = new LinkedList<>();

        connections.add(two);
        connections.add(three);

        Assert.assertTrue(network.getConnectedStationStops(one).containsAll(connections));
    }

    @Test
    public void getAllLineStopsAtShouldReturnEmptyListForNoStationFound() {
        Network network = new Network();
        Assert.assertTrue(network.getAllLineStationStopsAt(name2).isEmpty());
    }

    @Test
    public void getAllLineStopsAtShouldReturnStationIfOnlyOneStation() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        StationStop stop = network.getStationStop(name1, line1);
        assert network.getAllLineStationStopsAt(name1).size() == 1;
        Assert.assertTrue(network.getAllLineStationStopsAt(name1).contains(stop));
    }

    @Test
    public void getAllLineStopsAtShouldOnlyReturnStopsAtStation() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line2);
        network.addStationStop(name1, line2);
        network.addStationStop(name1, line3);

        StationStop stop = network.getStationStop(name2, line2);

        assert network.getAllLineStationStopsAt(name1).size() == 3;
        Assert.assertFalse(network.getAllLineStationStopsAt(name1).contains(stop));
    }

    @Test
    public void getAllLinesShouldReturnCollectionOfLines() {
        Network network = new Network();
        network.addStationStop(name1, line1);

        network.addStationStop(name1, line2);
        network.addStationStop(name1, line3);
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
        network.addStationStop(name1, line1);
        Assert.assertNull(network.getStationStop(name2, line1));
    }

    @Test
    public void getStopShouldGetStop(){
        Network network = new Network();
        network.addStationStop(name1, line1);
        StationStop stop = createValidStation();
        Assert.assertTrue(network.getStationStop(name1, line1).equals(stop));
    }

    //Add Stop Tests
    @Test
    public void addStopShouldReturnTrueAndAddStopToList(){
        Network network = new Network();
        Assert.assertTrue(network.addStationStop(name1, line1) && !network.getAllStationStops().isEmpty());
    }

    @Test
    public void addStopShouldReturnFalseIfNewStopAlreadyExists(){
        Network network = new Network();
        network.addStationStop(name1, line1);
        Assert.assertFalse(network.addStationStop(name1, line1));
    }

    //Remove Stop Tests
    @Test
    public void removeStopShouldRemoveStopFromList(){
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.removeStationStop(name1, line1);
        Assert.assertTrue(network.getAllStationStops().isEmpty());
    }

    @Test
    public void removeStopShouldNotRemoveAnythingWhenNoStopFound(){
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.removeStationStop(name2, name1);
        Assert.assertTrue(network.getAllStationStops().size() == 1);
    }

    @Test
    public void removeStopShouldAlsoRemoveAllConnectionsToStop() {
        Network network = new Network();
        network.addStationStop("York","A");
        network.addStationStop("Darlington","A");
        network.addStationStop("Newcastle","A");
        network.addStationStop("Berwick-upon-Tweed","A");
        network.addStationStop("Dunbar","A");
        network.addStationStop("Edinburgh Waverley","A");

        network.addStopConnection("York","A","Darlington","A",39);
        network.addStopConnection("York","A","Newcastle","A",28);
        network.addStopConnection("York","A","Berwick-upon-Tweed","A",28);
        network.addStopConnection("York","A","Dunbar","A",28);

        network.addStopConnection("Dunbar","A","Edinburgh Waverley","A",28);

        StationStop stop = network.getStationStop("York", "A");

        assert network.getConnectedStationStops(stop).size() == 4;
        network.removeAllStopConnections(stop);
        assert network.getAllStopConnections().size() == 2;

        Assert.assertTrue(network.getConnectedStationStops(stop).isEmpty());
    }


    //Add Path Tests
    @Test
    public void addConnectionShouldReturnFalseIfStopsNotFound() {
        Network network = new Network();
        StationStop stop1 = createValidStation();
        StationStop stop2 = new StationStop(name2, line2);
        Assert.assertFalse(network.addStopConnection(stop1, stop2, 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfFirstStopNotFound() {
        Network network = new Network();
        StationStop stop1 = createValidStation();
        StationStop stop2 = new StationStop(name2, line2);
        network.addStationStop(stop2.getName(), stop2.getLine());
        Assert.assertFalse(network.addStopConnection(stop1, network.getStationStop(name2, line2), 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfSecondStopNotFound() {
        Network network = new Network();
        StationStop stop1 = createValidStation();
        StationStop stop2 = new StationStop(name2, line2);
        network.addStationStop(stop2.getName(), stop2.getLine());
        Assert.assertFalse(network.addStopConnection(network.getStationStop(name2, line2), stop1, 10));
    }

    @Test
    public void addConnectionShouldReturnFalseIfBothStopsFoundButDistanceLessThanOne() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line2);
        Assert.assertFalse(network.addStopConnection(network.getStationStop(name1, line1), network.getStationStop(name2, line2), 0));
    }

    @Test
    public void addConnectionShouldReturnTrueIfBothStopsFoundAndDistanceGreaterThanZero() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line2);
        Assert.assertTrue(network.addStopConnection(network.getStationStop(name1, line1), network.getStationStop(name2, line2), 1));
    }

    @Test
    public void addConnectionShouldAddTwoConnectionsToConnectionList() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStopConnection(network.getStationStop(name1, line1), network.getStationStop(name2, line1), 10);
        Assert.assertTrue(network.getAllStopConnections().size() == 2);
    }

    @Test
    public void addConnectionShouldReturnFalseIfConnectionAlreadyExists() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStopConnection(network.getStationStop(name1, line1), network.getStationStop(name2, line1), 10);
        Assert.assertFalse(network.addStopConnection(name1, line1, name2, name1, 1));
    }

    //Remove Path Tests
    @Test
    public void removeConnectionShouldReturnFalseIfNoConnections() {
        Network network = new Network();
        Assert.assertFalse(network.removeStopConnectionBetween(from, to));
    }

    @Test
    public void removeConnectionShouldReturnFalseWhenStopsExistButNoConnection() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        Assert.assertFalse(network.removeStopConnectionBetween(network.getStationStop(name1, line1), network.getStationStop(name2, line1)));
    }

    @Test
    public void removeConnectionShouldReturnShouldRemoveConnection() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStopConnection(name1, line1, name2, line1, 6);
        Assert.assertTrue(network.removeStopConnectionBetween(network.getStationStop(name1, line1), network.getStationStop(name2, line1)));
    }

    @Test
    public void removeConnectionShouldRemoveCorrectConnection() {
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStationStop(name3, line1);

        network.addStopConnection(name1, line1, name2, line1, 6);
        network.addStopConnection(name2, line1, name3, line1, 10);

        StationStop two = network.getStationStop(name2, line1);
        StationStop three = network.getStationStop(name3, line1);

        assert network.getOneWayStopConnection(three, two) != null;
        network.removeStopConnectionBetween(three, two);

        StopConnection connection1 = network.getOneWayStopConnection(three, two);
        StopConnection connection2 = network.getOneWayStopConnection(two, three);

        Assert.assertTrue(connection1 == null && connection2 == null);
    }

    @Test
    public void removeConnectionShouldAllowStringParams(){
        Network network = new Network();
        network.addStationStop(name1, line1);
        network.addStationStop(name2, line1);
        network.addStationStop(name3, line1);

        StationStop one = network.getStationStop(name1, line1);
        StationStop two = network.getStationStop(name2, line1);
        StationStop three = network.getStationStop(name3, line1);

        network.addStopConnection(name1, line1, name2, line1, 6);
        network.addStopConnection(name1, line1, name3, line1, 10);

        assert network.getConnectedStationStops(one).contains(three);

        network.removeStopConnectionBetween(name1, line1, name3, line1);

        Assert.assertFalse(network.getConnectedStationStops(one).contains(three));
    }

    //Private test helpers
    //Stop Creation
    private String name = "Newcastle";
    private String line = "A";
    private String lineToString = "Stop: Newcastle, Line: A";

    private StationStop createValidStation() {
        StationStop stop = new StationStop();
        stop.name = name;
        stop.line = line1;
        return stop;
    }

    //Path Creation
    private int cost = 5;
    private String line1 = "A";
    private String line2 = "B";
    private String line3 = "C";
    private String name1 = "Newcastle";
    private String name2 = "Morpeth";
    private String name3 = "Darlington";
    private StationStop from = new StationStop(name1, line1);
    private StationStop to = new StationStop(name2, line1);

    private StopConnection createValidConnection() {
        return new StopConnection(from, to, cost);
    }

}
