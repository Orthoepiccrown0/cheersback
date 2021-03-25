package com.cheers.main.model.account;

import com.cheers.main.model.enums.Sex;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User extends Accounts {

    private String surname;

    private Date birthday;

    private Sex sex;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
