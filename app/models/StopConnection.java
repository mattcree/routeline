package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by Cree on 24/03/2017.
 */
@Entity
public class StopConnection extends Model{

    @Id
    public Long id;
    @Constraints.Required
    @ManyToOne
    public StationStop stopA;
    @Constraints.Required
    @ManyToOne
    public StationStop stopB;
    @Constraints.Required
    public Long distance;

    public static Model.Finder<Long, StopConnection> find = new Finder(Long.class, StopConnection.class);

}
