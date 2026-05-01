package sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import sample.common.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public SecurityConfig() {
        System.out.println("★SecurityConfigがインスタンス化されました★");
    }
	
	@Autowired
    private LoginService loginService;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("★securityFilterChainメソッドが実行されました★");
        
        http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/","/login", "/register", "/css/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/tasks", true)
            .permitAll()
        )
        // ここで作成したサービスをセットする
        .userDetailsService(loginService); 
        
        return http.build();
    }
}