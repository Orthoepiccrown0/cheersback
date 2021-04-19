package com.cheers.main.service.impl;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.repository.CompanyRepository;
import com.cheers.main.repository.UserRepository;
import com.cheers.main.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LoginService")
public class LoginService implements ILoginService {

    @Qualifier
    private UserRepository userRepository;

    @Qualifier
    private CompanyRepository companyRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public Company findCompanyByEmailAndPassword(String email, String password) {
        return companyRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public void subscribeToEvent(User user, Event event) {
        user.getSubscribedEvents().add(event);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(findUserById(id));
    }

    @Override
    public void deleteCompany(String id) {
        companyRepository.delete(findCompanyById(id));
    }

    @Override
    public boolean isEmailUsed(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        Company company = companyRepository.findByEmail(email).orElse(null);

        return user != null || company != null;
    }
}
