package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Created by Cree on 24/03/2017.
 */
@Entity
public class Connection extends Model{

    @Id
    public Long id;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private Stop from;
    @Constraints.Required
    private Stop to;
    @Constraints.Required
    private int distance;

    public Connection(Stop from, Stop to, int distance) {
        this.name = from.getName() + " to " + to.getName();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Stop from() {
        return from;
    }

    public Stop to() {
        return to;
    }

    public int distance() {
        return distance;
    }

    public boolean isLineChange() {
        return !from.getLine().equals(to.getLine());
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }

}
