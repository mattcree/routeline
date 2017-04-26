package routefindertest;

import models.routefinder.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cree on 03/04/2017.
 */
public class RoutefinderTest {

    @Test
    public void createRouteFinderShouldNotBeNull() {
        Routefinder rf = new Routefinder(createNetwork());
        Assert.assertNotNull(rf);
    }

    //Helpers for generating routes


    public Network createNetwork() {
        Network network = new Network();
        //Line A
        network.addStationStop("York","A");
        network.addStationStop("Darlington","A");
        network.addStationStop("Newcastle","A");
        network.addStationStop("Berwick-upon-Tweed","A");
        network.addStationStop("Dunbar","A");
        network.addStationStop("Edinburgh Waverley","A");
        network.addStationStop("Edinburgh Haymarket","A");
        network.addStationStop("Stirling","A");
        network.addStationStop("Perth","A");
        network.addStationStop("Pitlochry","A");
        network.addStationStop("Kingussie","A");
        network.addStationStop("Aviemore","A");
        network.addStationStop("Inverkeithing","A");
        network.addStationStop("Leuchars","A");
        network.addStationStop("Dundee","A");
        network.addStationStop("Montrose","A");

        //Line B
        network.addStationStop("York","B");
        network.addStationStop("Northallerton","B");
        network.addStationStop("Darlington","B");
        network.addStationStop("Durham","B");
        network.addStationStop("Newcastle","B");
        network.addStationStop("Sunderland","B");
        network.addStationStop("Morpeth","B");
        network.addStationStop("Alnmouth","B");
        network.addStationStop("Edinburgh Waverley","B");
        network.addStationStop("Edinburgh Haymarket","B");
        network.addStationStop("Motherwell","B");
        network.addStationStop("Glasgows","B");

        //Connections
        //Line A
        network.addStopConnection("York","A","Darlington","A",39);
        network.addStopConnection("Darlington","A","Newcastle","A",28);
        network.addStopConnection("Newcastle","A","Berwick-upon-Tweed","A",41);
        network.addStopConnection("Berwick-upon-Tweed","A","Dunbar","A",22);
        network.addStopConnection("Dunbar","A","Edinburgh Waverley","A",24);
        network.addStopConnection("Edinburgh Waverley","A","Edinburgh Haymarket","A",5);

        //Left of Fork
        network.addStopConnection("Edinburgh Haymarket","A","Stirling","A",80);
        network.addStopConnection("Stirling","A","Perth","A",34);
        network.addStopConnection("Perth","A","Pitlochry","A",31);
        network.addStopConnection("Pitlochry","A","Kingussie","A",45);
        network.addStopConnection("Kingussie","A","Aviemore","A",11);
        network.addStopConnection("Aviemore","A","Inverness","A",43);

        //Right of Fork
        network.addStopConnection("Edinburgh Haymarket","A","Inverkeithing","A",22);
        network.addStopConnection("Inverkeithing","A","Leuchars","A",40);
        network.addStopConnection("Leuchars","A","Dundee","A",13);
        network.addStopConnection("Dundee","A","Montrose","A",30);
        network.addStopConnection("Montrose","A","Aberdeen","A",38);

        //Line B
        network.addStopConnection("York","B","Northallerton","B",20);
        network.addStopConnection("Northallerton","B","Darlington","B",20);
        network.addStopConnection("Darlington","B","Durham","B",15);
        network.addStopConnection("Durham","B","Newcastle","B",14);
        network.addStopConnection("Newcastle","B","Sunderland","B",20);
        network.addStopConnection("Newcastle","B","Morpeth","B",12);
        network.addStopConnection("Morpeth","B","Alnmouth","B",15);
        network.addStopConnection("Alnmouth","B","Edinburgh Waverley","B",15);
        network.addStopConnection("Edinburgh Waverley","B","Edinburgh Haymarket","B",5);
        network.addStopConnection("Edinburgh Haymarket","B","Motherwell","B",45);
        network.addStopConnection("Motherwell","B","Glasgow","B",20);

        //Connections between lines
        network.addStopConnection("York","A","York","B",5);
        network.addStopConnection("Darlington","A","Darlington","B",5);
        network.addStopConnection("Newcastle","A","Newcastle","B",5);
        network.addStopConnection("Edinburgh Waverley","A","Edinburgh Waverley","B",5);
        network.addStopConnection("Edinburgh Haymarket","A","Edinburgh Haymarket","B",5);

        return network;
    }


}
