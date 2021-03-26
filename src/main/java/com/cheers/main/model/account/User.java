package com.cheers.main.model.account;

import com.cheers.main.model.enums.Gender;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User extends Accounts {

    private String surname;

    private Date birthday;

    private Gender gender;

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

    public Gender getSex() {
        return gender;
    }

    public void setSex(Gender gender) {
        this.gender = gender;
    }
}
