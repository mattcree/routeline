package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;


import javax.persistence.*;


/**
 * Created by Cree on 24/03/2017.
 */
@Entity
public class StopConnection extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Constraints.Required
    @ManyToOne
    public StationStop stopA;
    @Constraints.Required
    @ManyToOne
    public StationStop stopB;
    @Constraints.Required
    public int time;

    public static Finder<Long, StopConnection> find = new Finder<>(StopConnection.class);

    public StationStop from() {
        return stopA;
    }

    public StationStop to() {
        return stopB;
    }

    public int time() {
        return time;
    }

    public boolean isLineChange() {
        return !stopA.getLine().equals(stopB.getLine());
    }

    public String toString() {
        return stopA.toString() + " to " + stopB.toString() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StopConnection that = (StopConnection) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
