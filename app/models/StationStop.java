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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String line;

    public static Finder<Long, StationStop> find = new Finder<>(StationStop.class);

    public String toString() {
        return name + " " + line;
    }
}
