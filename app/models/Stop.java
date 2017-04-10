package models;

import java.util.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.annotation.EnumValue;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Stop extends Model implements BasicModel<Long>{
	
    @Id
	private Long key;

	@Basic
	@Required
	private String name;

	@Basic
	@Required
	private String line;
	
	public void setKey(Long key) {
		this.key = key;
	}
	public Long getKey() {
		return key;
	}

    public String getName(){
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}

    public void setLine(String line) {
		this.line = line;
	}

    public String getLine(){
        return line;
    }


    @Override
    public String toString() {
        return name + " on Line " + line;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Stop stop = (Stop) object;
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
