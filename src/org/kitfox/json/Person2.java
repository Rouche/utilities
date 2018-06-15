/**
 *
 */
package org.kitfox.json;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author larj15
 */
public class Person2 {
    String name;
    boolean dumb;
    Date birth;

    @JsonManagedReference
    private List<Adress2> adresses;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dumb
     */
    public boolean isDumb() {
        return dumb;
    }

    /**
     * @param dumb the dumb to set
     */
    public void setDumb(boolean dumb) {
        this.dumb = dumb;
    }

    /**
     * @return the birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth the birth to set
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * @return the adresses
     */
    public List<Adress2> getAdresses() {
        return adresses;
    }

    /**
     * @param adresses the adresses to set
     */
    public void setAdresses(List<Adress2> adresses) {
        this.adresses = adresses;
    }

}
