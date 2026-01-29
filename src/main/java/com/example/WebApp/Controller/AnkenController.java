package com.example.WebApp.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebApp.Entity.Anken;
import com.example.WebApp.Entity.Department;
import com.example.WebApp.Repository.AnkenRepository;
import com.example.WebApp.Repository.DepartmentRepository;

@Controller
public class AnkenController {

    private final AnkenRepository ankenRepository;
    private final DepartmentRepository departmentRepository;

    public AnkenController(AnkenRepository ankenRepository,
                           DepartmentRepository departmentRepository) {
        this.ankenRepository = ankenRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/AK01")
    public String showAnkenList(Model model) {
        List<Anken> ankenList = ankenRepository.findAll();
        model.addAttribute("ankenList", ankenList);
        return "AK01";
    }

    @GetMapping("/AK02")
    public String showCreateForm(Model model) {
        model.addAttribute("anken", new Anken());
        model.addAttribute("departments", departmentRepository.findAll());
        return "AK02";
    }

    @PostMapping("/saveAnken")
    public String saveAnken(@ModelAttribute Anken anken, Model model) {


        if (anken.getDepartment() == null || anken.getDepartment().getDepartment_id() == null) {
            model.addAttribute("errorMsg", "部署を選択してください");
            model.addAttribute("departments", departmentRepository.findAll());
            return "AK02";
        }

        Department dept = departmentRepository
                .findById(anken.getDepartment().getDepartment_id())
                .orElse(null);

        anken.setDepartment(dept);

        // 保存
        ankenRepository.save(anken);

        return "redirect:/AK01";
    }


    @GetMapping("/editAnken")
    public String editAnken(@RequestParam Long id, Model model) {
        Anken anken = ankenRepository.findById(id).orElse(null);
        model.addAttribute("anken", anken);
        model.addAttribute("departments", departmentRepository.findAll());
        return "AK02";
    }

    @GetMapping("/deleteAnken")
    public String deleteAnken(@RequestParam Long id) {
        ankenRepository.deleteById(id);
        return "redirect:/AK01";
    }

    @GetMapping("/anken/by-department")
    @ResponseBody
    public List<Anken> getAnkenByDepartment(@RequestParam Long departmentId) {
        return ankenRepository.findByDepartmentId(departmentId);
    }
}
