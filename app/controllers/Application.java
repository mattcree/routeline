package controllers;
import models.routefinder.Network;
import models.routefinder.Routefinder;
import play.libs.Json;
import play.mvc.*;
import views.html.*;


public class Application extends Controller {

    private static Network network = setUpNetwork();
    private static Routefinder rf = new Routefinder(network);

    private static Network setUpNetwork() {
        Network network = new Network();
        //Line A
        network.addStop("York","A");
        network.addStop("Darlington","A");
        network.addStop("Newcastle","A");
        network.addStop("Berwick-upon-Tweed","A");
        network.addStop("Dunbar","A");
        network.addStop("Edinburgh Waverley","A");
        network.addStop("Edinburgh Haymarket","A");
        network.addStop("Stirling","A");
        network.addStop("Perth","A");
        network.addStop("Pitlochry","A");
        network.addStop("Kingussie","A");
        network.addStop("Aviemore","A");
        network.addStop("Inverkeithing","A");
        network.addStop("Leuchars","A");
        network.addStop("Dundee","A");
        network.addStop("Montrose","A");
        network.addStop("Aberdeen","A");

        //Line B
        network.addStop("York","B");
        network.addStop("Northallerton","B");
        network.addStop("Darlington","B");
        network.addStop("Durham","B");
        network.addStop("Newcastle","B");
        network.addStop("Sunderland","B");
        network.addStop("Morpeth","B");
        network.addStop("Alnmouth","B");
        network.addStop("Edinburgh Waverley","B");
        network.addStop("Edinburgh Haymarket","B");
        network.addStop("Motherwell","B");
        network.addStop("Glasgow","B");

        //Connections
        //Line A
        network.addConnection("York","A","Darlington","A",39);
        network.addConnection("Darlington","A","Newcastle","A",28);
        network.addConnection("Newcastle","A","Berwick-upon-Tweed","A",41);
        network.addConnection("Berwick-upon-Tweed","A","Dunbar","A",22);
        network.addConnection("Dunbar","A","Edinburgh Waverley","A",24);
        network.addConnection("Edinburgh Waverley","A","Edinburgh Haymarket","A",5);

        //Left of Fork
        network.addConnection("Edinburgh Haymarket","A","Stirling","A",80);
        network.addConnection("Stirling","A","Perth","A",34);
        network.addConnection("Perth","A","Pitlochry","A",31);
        network.addConnection("Pitlochry","A","Kingussie","A",45);
        network.addConnection("Kingussie","A","Aviemore","A",11);
        network.addConnection("Aviemore","A","Inverness","A",43);

        //Right of Fork
        network.addConnection("Edinburgh Haymarket","A","Inverkeithing","A",22);
        network.addConnection("Inverkeithing","A","Leuchars","A",40);
        network.addConnection("Leuchars","A","Dundee","A",13);
        network.addConnection("Dundee","A","Montrose","A",30);
        network.addConnection("Montrose","A","Aberdeen","A",38);

        //Line B
        network.addConnection("York","B","Northallerton","B",20);
        network.addConnection("Northallerton","B","Darlington","B",20);
        network.addConnection("Darlington","B","Durham","B",15);
        network.addConnection("Durham","B","Newcastle","B",14);
        network.addConnection("Newcastle","B","Sunderland","B",20);
        network.addConnection("Newcastle","B","Morpeth","B",12);
        network.addConnection("Morpeth","B","Alnmouth","B",15);
        network.addConnection("Alnmouth","B","Edinburgh Waverley","B",15);
        network.addConnection("Edinburgh Waverley","B","Edinburgh Haymarket","B",5);
        network.addConnection("Edinburgh Haymarket","B","Motherwell","B",45);
        network.addConnection("Motherwell","B","Glasgow","B",20);

        //Connections between lines
        network.addConnection("York","A","York","B",5);
        network.addConnection("Darlington","A","Darlington","B",5);
        network.addConnection("Newcastle","A","Newcastle","B",5);
        network.addConnection("Edinburgh Waverley","A","Edinburgh Waverley","B",5);
        network.addConnection("Edinburgh Haymarket","A","Edinburgh Haymarket","B",5);
        return network;
    }



    public static Result index()  {
        return ok(index.render(stationPicker.render()));
    }




    //Public API Functions
    public static Result getStops() {
        return ok(Json.toJson(network.getAllStops()));
    }




}
