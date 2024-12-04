package example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @GetMapping
    public String redirectToPage(Authentication authentication) {
        String username = authentication.getName();
        return "redirect:/LKabinet/" + username;
    }
}