/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

import java.util.Date;

/**
 *
 * @author m27
 */
public class Person implements Comparable {

    private String fullname;
    private Date birthday;
    private Gender gender;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person(String fullname, Date birthday, Gender gender) {
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Person)) {
            throw new UnsupportedOperationException("Type must be Person");
        }
        return this.fullname.compareTo(((Person) o).fullname);
    }

    @Override
    public String toString() {
        return String.format("Person: %s", fullname);
    }

    public static enum Gender {
        MALE, FEMALE
    }

}
