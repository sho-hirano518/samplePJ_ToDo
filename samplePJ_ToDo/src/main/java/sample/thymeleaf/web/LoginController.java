package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // ユーザー登録画面表示
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // ユーザー登録処理
    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username, 
            @RequestParam("password") String password) {
        loginService.registerUser(username, password);
        return "redirect:/login";
    }

    // ログイン画面表示
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(
    		@RequestParam("username") String username,
    		@RequestParam("password") String password,
    		HttpSession session) {
    	System.out.println("--- LoginController.login POST開始 ---");
    	
        Login user = loginService.authenticate(username, password);
        if (user != null) {
        	System.out.println("Controller: 認証成功。/tasks へリダイレクトします。");
        	
//        	Authentication auth = new UsernamePasswordAuthenticationToken(
//                    user.getUsername(), 
//                    null, 
//                    AuthorityUtils.createAuthorityList("ROLE_USER")
//                );
//                SecurityContextHolder.getContext().setAuthentication(auth);
        	
            session.setAttribute("user", user);
            return "redirect:/tasks";
        } else {
        	System.out.println("Controller: 認証失敗。/login?error へリダイレクトします。");
        	return "redirect:/login?error"; // 認証エラー（再ログイン画面）
        }
        
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}