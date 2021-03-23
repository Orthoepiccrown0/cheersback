package com.cheers.main.model.account;

import com.cheers.main.model.enums.Sex;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User extends Account{

    private String surname;

    private Date birthday;

    private Sex sex;


}
