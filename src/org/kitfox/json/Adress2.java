/**
 *
 */
package org.kitfox.json;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author larj15
 */
public class Adress2 {
    String street;
    int number;

    @JsonBackReference
    Person2 person;

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the person
     */
    public Person2 getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person2 person) {
        this.person = person;
    }

}
