package example.controller;

import example.entity.User;
import example.repository.UserRepository;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


//    @PostMapping("/")
//    public String foo(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = user.getUsername();
//        model.addAttribute("username", username);
//        return "/index";
//    }


}




