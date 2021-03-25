package com.cheers.main.container;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

public class LoginResponse {

    private User user;

    private Company company;

    public LoginResponse(User user) {
        this.user = user;
    }

    public LoginResponse(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public Company getCompany() {
        return company;
    }

}
