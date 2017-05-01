package routefindertest;

import models.Route;
import models.StationStop;
import models.StopConnection;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by Cree on 30/04/2017.
 */
public class RouteTest {

    @Test
    public void newRouteShouldCreateValidRoute() {
        Route route = createValidRoute();
        Assert.assertNotNull(route);
    }

    @Test
    public void routeShouldReturnCorrectStart() {
        Route route = createValidRoute();
        Assert.assertSame(route.start, stop1);
    }

    @Test
    public void routeShouldReturnCorrectDestination() {
        Route route = createValidRoute();
        Assert.assertSame(route.destination, stop6);
    }

    @Test
    public void routeShouldReturnCorrectJourneyTime() {
        Route route = createValidRoute();
        Assert.assertTrue(route.timeInMinutes == 45);
    }

    @Test
    public void routeShouldReturnCorrectNumberOfChanges() {
        Route route = createValidRoute();
        Assert.assertTrue(route.numberOfChanges == 1);
    }

    //Private test helpers
    StationStop stop1 = createValidStationFromString("Newcastle", "A");
    StationStop stop2 = createValidStationFromString("Darlington", "A");
    StationStop stop3 = createValidStationFromString("Leeds", "A");
    StationStop stop4 = createValidStationFromString("Dunbar", "A");
    StationStop stop5 = createValidStationFromString("Dunbar", "B");
    StationStop stop6 = createValidStationFromString("Sheffield", "B");

    StopConnection connection1 = createValidStopConnectionFromStops(stop1, stop2, 10);
    StopConnection connection2 = createValidStopConnectionFromStops(stop2, stop3, 10);
    StopConnection connection3 = createValidStopConnectionFromStops(stop3, stop4, 10);
    StopConnection connection4 = createValidStopConnectionFromStops(stop4, stop5, 5);
    StopConnection connection5 = createValidStopConnectionFromStops(stop5, stop6, 10);


    private Route createValidRoute() {
        LinkedList<StationStop> stationStops = new LinkedList<>();
        LinkedList<StopConnection> stopConnections = new LinkedList<>();

        stationStops.add(stop1);
        stationStops.add(stop2);
        stationStops.add(stop3);
        stationStops.add(stop4);
        stationStops.add(stop5);
        stationStops.add(stop6);

        stopConnections.add(connection1);
        stopConnections.add(connection2);
        stopConnections.add(connection3);
        stopConnections.add(connection4);
        stopConnections.add(connection5);

        Route route = new Route(stationStops, stopConnections);

        return route;
    }

    private StationStop createValidStationFromString(String name, String line) {
        StationStop stop = new StationStop();
        stop.name = name;
        stop.line = line;
        return stop;
    }

    private StopConnection createValidStopConnectionFromStops(StationStop stop1, StationStop stop2, int time) {
        StopConnection stopConnection = new StopConnection();
        stopConnection.stopA = stop1;
        stopConnection.stopB = stop2;
        stopConnection.time = time;
        return stopConnection;
    }


}
