package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    
    // BCrypt方式のエンコーダーを用意
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Login authenticate(String username, String password) {
        // IDをキーにユーザー情報を取得
        Login user = loginMapper.findById(username);
        // 重要：DBのハッシュ値と、入力された平文を照合
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public void registerUser(String username, String password) {
        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // 保存前にハッシュ化する
        loginMapper.insert(newUser);
    }
}