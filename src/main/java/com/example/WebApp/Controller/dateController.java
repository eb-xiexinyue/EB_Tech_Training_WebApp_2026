package com.example.WebApp.Controller;

import com.example.WebApp.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dateController {
    @GetMapping("/date0114")
    public String date0114(Model model) {
        return "date0114";
    }
}
