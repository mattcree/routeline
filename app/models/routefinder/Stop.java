package models.routefinder;

/**
 * Created by Cree on 15/04/2017.
 */
public class Stop {

    private String name;
    private String line;

    public Stop(String name, String line){
        this.name = name;
        this.line = line;
    }

    public String getName(){
        return name;
    }

    public String getLine(){
        return line;
    }

    //Variant of .equals()
    public boolean stopsAtSameStation(Stop stop) {
        return this.getName().equals(stop.getName());
    }

    @Override
    public String toString() {
        return name + " on Line " + line;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        Stop stop = (Stop) object;
        if (!name.equals(stop.name)) return false;
        return line.equals(stop.line);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + line.hashCode();
        return result;
    }

}
