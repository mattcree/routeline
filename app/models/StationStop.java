package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StationStop extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String line;

    public StationStop(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public String getName(){
        return name;
    }

    public String getLine(){
        return line;
    }

    public static Finder<Long, StationStop> find = new Finder<>(StationStop.class);

    @Override
    public String toString() {
        return this.getName() + " on " + this.getLine() + " line";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        StationStop stop = (StationStop) object;
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
