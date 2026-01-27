package com.example.WebApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public MainController() {
    }

    @GetMapping("/MAIN")
    public String getMainPage() {
        return "MAIN";
    }
}
