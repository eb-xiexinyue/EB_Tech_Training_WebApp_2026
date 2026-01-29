package com.example.WebApp.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebApp.Entity.Anken;
import com.example.WebApp.Repository.AnkenRepository;

@Controller
public class AnkenController {

    private final AnkenRepository ankenRepository;

    public AnkenController(AnkenRepository ankenRepository) {
        this.ankenRepository = ankenRepository;
    }

    @GetMapping("/anken/by-department")
    @ResponseBody
    public List<Anken> ankenList(@RequestParam Long departmentId) {
        return ankenRepository.findByDepartmentId(departmentId);
    }
}
