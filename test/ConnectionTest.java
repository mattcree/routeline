import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cree on 25/03/2017.
 */
public class ConnectionTest {

    @Test
    public void createConnectionShouldCreateValidConnection() {
        Connection connection = createValidConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void getFromShouldReturnConnectionOrigin() {
        Connection connection = createValidConnection();
        Assert.assertSame(connection.from(), from);
    }

    @Test
    public void getToShouldReturnConnectionDestination() {
        Connection connection = createValidConnection();
        Assert.assertSame(connection.to(), to);
    }

    @Test
    public void getDistanceShouldReturnConnectionDistance() {
        Connection connection = createValidConnection();
        Assert.assertTrue(connection.distance() == cost);
    }

    @Test
    public void toStringShouldReturnValidRepresentationOfConnectionAsString() {
        Connection connection = createValidConnection();
        Assert.assertTrue(connection.toString().equals(name1 + " to " + name2));
    }

    @Test
    public void getNameShouldGetConnectionName() {
        Connection connection = createValidConnection();
        Assert.assertTrue(connection.getName().equals(name1 + " to " + name2));
    }

    @Test
    public void isLineChangeShouldReturnTrueIfChangeOfLines() {
        Stop stop1 = new Stop("Barry", "A");
        Stop stop2 = new Stop("Barry2", "B");

        Connection connection = new Connection(stop1, stop2, 15);
        Assert.assertTrue(connection.isLineChange());
    }

    @Test
    public void isLineChangeShouldReturnFalseIfNotLineChange() {
        Stop stop1 = new Stop("Barry", "A");
        Stop stop2 = new Stop("Barry2", "A");

        Connection connection = new Connection(stop1, stop2, 15);
        Assert.assertFalse(connection.isLineChange());
    }

    //helpers for tests
    private Connection createValidConnection() {
        return new Connection(from, to, cost);
    }

    private int cost = 5;
    private String line = "A";
    private String name1 = "Newcastle";
    private String name2 = "Morpeth";

    private Stop from = new Stop(name1, line);
    private Stop to = new Stop(name2, line);


}
