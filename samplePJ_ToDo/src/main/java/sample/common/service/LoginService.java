package sample.common.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import sample.common.dao.entity.Login;

public interface LoginService extends UserDetailsService {
    Login authenticate(String username, String password);
    void registerUser(String username, String password);
}