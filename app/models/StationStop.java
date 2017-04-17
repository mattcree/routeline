package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StationStop extends Model{

    @Id
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String line;

    public static Model.Finder<Long, StationStop> find = new Finder(Long.class, StationStop.class);

    public String toString() {
        return name + " " + line;
    }
}
