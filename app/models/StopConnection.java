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
    public Long distance;

    public static Finder<Long, StopConnection> find = new Finder<>(StopConnection.class);

    public String toString() {
        return stopA.toString() + " to " + stopB.toString() ;
    }

}
