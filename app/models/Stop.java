package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Stop extends Model{
    @Id
    public Long id;

    @Constraints.Required
    private String name;
    @Constraints.Required
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

    public static Model.Finder<Long, Stop> find = new Finder(Long.class,Stop.class);

    @Override
    public String toString() {
        return name + " on Line " + line;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
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
