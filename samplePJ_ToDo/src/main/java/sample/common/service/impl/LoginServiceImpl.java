package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Login authenticate(String username, String password) {
    	System.out.println("--- LoginServiceImpl.authenticate 開始 ---");
    	System.out.println("ユーザー名: " + username);
    	
        Login user = loginMapper.findByUsername(username);
        if (user == null) {
            System.out.println("結果: DBにユーザーが存在しません");
            return null;
        }
        System.out.println("DBから取得したパスワード(ハッシュ): " + user.getPassword());
        System.out.println("入力されたパスワード: " + password);
        boolean isMatch = passwordEncoder.matches(password, user.getPassword());
        System.out.println("BCrypt照合結果: " + isMatch);
        if (user != null && isMatch) {
        	System.out.println("認証成功！");
            return user;
        } else {
        	System.out.println("認証失敗...: パスワードが一致しません");
        	return null;
        }
    }

    @Override
    @Transactional
    public void registerUser(String username, String password) {
    	
    	String hashedPassword = passwordEncoder.encode(password);
	    System.out.println("--- ユーザー登録処理 ---");
	    System.out.println("登録名: " + username);
	    System.out.println("生成されたハッシュ: " + hashedPassword);
    	
        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword); // 保存前にハッシュ化する
        loginMapper.insert(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = loginMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("ユーザー [" + username + "] が見つかりません");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}