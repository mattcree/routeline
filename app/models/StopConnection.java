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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    @OneToOne
    public StationStop stopA;
    @Constraints.Required
    @OneToOne
    public StationStop stopB;
    @Constraints.Required
    public Long distance;

    public static Finder<Long, StopConnection> find = new Finder<>(StopConnection.class);

}
