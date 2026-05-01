package sample.thymeleaf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sample.common.dao.entity.Login;
import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ログインチェック用セッション判定
//    private boolean isNotLoggedIn(HttpSession session) {
//        return session.getAttribute("user") == null;
//    }

    // タスク一覧表示
    @GetMapping
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            HttpSession session, 
            Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("DEBUG: TaskController.list() 実行。ユーザー名: " + username);
        
        // ユーザー情報取得
        Login user = (Login) session.getAttribute("user");
//        String username = user.getUsername();
        if (user == null) {
            System.out.println("WARN: セッションに user オブジェクトがありません。再セットを検討してください。");
        }
        
        // データの取得とページネーションの計算
        List<Task> tasks = taskService.findList(username, page);
        long totalCount = taskService.countByUsername(username);
        int totalPages = (int) Math.ceil((double) totalCount / 5);
        if (totalPages == 0) totalPages = 1;
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "tasks/list";
    }

    // 新規登録フォーム表示
    @GetMapping("/new")
    public String newForm(
    		HttpSession session,
    		Model model) {
        // ログインチェック
//        if (isNotLoggedIn(session)) return "redirect:/login";
        
        model.addAttribute("task", new Task());
        return "tasks/form-new";
    }

    // 新規登録実行
    @PostMapping
    public String create(
    		@ModelAttribute Task task,
    		HttpSession session) {
        // ログインチェック
//        if (isNotLoggedIn(session)) return "redirect:/login";

        // ユーザー情報取得
    	Login user = (Login) session.getAttribute("user");
    	if (user != null) {
            task.setUsername(user.getUsername());
        }
        taskService.insert(task);
        return "redirect:/tasks";
    }

    // 編集フォーム表示
    @GetMapping("/edit/{id}")
    public String editForm(
    		@PathVariable("id") Integer id,
    		HttpSession session,
    		Model model) {
        // ログインチェック
//        if (isNotLoggedIn(session)) return "redirect:/login";
    	Login user = (Login) session.getAttribute("user");
        Task task = taskService.findById(id, user.getUsername());
        if (task == null) return "redirect:/tasks";
        // コンソール
//        System.out.println("--- デバッグ開始 ---");
//        System.out.println("ID: " + id);
//        System.out.println("タイトル: " + task.getTitle());
//        System.out.println("開始日: " + task.getStartDate());
//        System.out.println("終了日: " + task.getEndDate());
//        System.out.println("--- デバッグ終了 ---");

        model.addAttribute("task", task);
        return "tasks/form-edit";
    }
    
    // タスク更新実行
    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Integer id,
            @ModelAttribute Task task, 
            HttpSession session) {
        // ログインチェック
//        if (isNotLoggedIn(session)) return "redirect:/login";

        // ユーザー情報取得
        Login user = (Login) session.getAttribute("user");
        task.setUsername(user.getUsername());
        task.setTaskId(id);
        
        taskService.update(task);
        return "redirect:/tasks";
    }

    // 削除実行
    @PostMapping("/delete/{id}")
    public String delete(
    		@PathVariable("id") Integer id,
    		HttpSession session) {
        // ログインチェック
//        if (isNotLoggedIn(session)) return "redirect:/login";

        Login user = (Login) session.getAttribute("user");
        taskService.delete(id, user.getUsername());
        return "redirect:/tasks";
    }
    
}