package com.example.WebApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebApp.Entity.User;
import com.example.WebApp.Repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    
    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }
    
    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable Long id) {
    	User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }
    
    @PostMapping("/saveUser")
    public String save(@ModelAttribute User user) {
    	userRepository.save(user);
    	return "redirect:/users";
    }
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
    	userRepository.deleteById(id);
    	return "redirect:/users";
    }
    
}