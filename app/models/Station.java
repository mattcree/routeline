package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Cree on 07/05/2017.
 */
@Entity
public class Station extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Constraints.Required
    public String name;

    public Station(String name) {
        this.name = name;
    }

    public static Finder<Long, Station> find = new Finder<>(Station.class);

    public String toString() {
        return name;
    }

}
