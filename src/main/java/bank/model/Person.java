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
public class Person {

    private String fullname;
    private Date birthday;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Person(String fullname, Date birthday) {
        this.fullname = fullname;
        this.birthday = birthday;
    }

}
