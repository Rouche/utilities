/**
 *
 */
package org.kitfox.jwt;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author larj15
 */
public class Person {
    String name;
    boolean dumb;
    LocalDate birth;
    BigDecimal money;

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
    public LocalDate getBirth() {
        return birth;
    }

    /**
     * @param birth the birth to set
     */
    public void setBirth(LocalDate birth) {
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
}
