package com.cheers.main.model.account;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    private String id;

    private String email;

    private String password;

    private String name;

    private String avatar;

}
