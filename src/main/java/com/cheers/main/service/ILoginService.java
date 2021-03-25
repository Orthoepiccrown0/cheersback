package com.cheers.main.service;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

import java.util.List;


public interface ILoginService {

    List<User> getAll();

    void saveUser(User user);

    void saveCompany(Company company);

    User findUserById(String id);

    Company findCompanyById(String id);

    User findUserByEmailAndPassword(String email, String password);

    Company findCompanyByEmailAndPassword(String email, String password);

    void deleteUser(String id);

    void deleteCompany(String id);

    boolean isEmailUsed(String email);
}
