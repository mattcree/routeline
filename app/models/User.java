package models;

import play.db.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Cree on 17/04/2017.
 */
public class User {
    @Id
    public Integer id;
    public String username;
    public String authToken;

    public static Model.Finder<Integer,User> find = new Model.Finder(Integer.class, User.class);
}
