package com.cheers.main.model.account;

import javax.persistence.Entity;

@Entity
public class Company extends Accounts {

    private String pIva;

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }
}
