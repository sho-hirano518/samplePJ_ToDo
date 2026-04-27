package sample.common.service;

import sample.common.dao.entity.Login;

public interface LoginService {
    Login authenticate(String username, String password);
    void registerUser(String username, String password);
}