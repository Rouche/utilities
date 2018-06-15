/**
 *
 */
package org.kitfox.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author larj15
 */
public class Person {
    String name;
    boolean dumb;
    Date birth;

    BigDecimal money;

    @JsonManagedReference
    private List<Adress> adresses;

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
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return the adresses
     */
    public void addAdress(Adress a) {
        if (adresses == null) {
            adresses = new ArrayList<Adress>();
        }

        a.setPerson(this);
        adresses.add(a);
    }

    /**
     * @return the adresses
     */
    public List<Adress> getAdresses() {
        return adresses;
    }

    /**
     * @param adresses the adresses to set
     */
    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }
}
