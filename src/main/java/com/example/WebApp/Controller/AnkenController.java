package com.example.WebApp.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.WebApp.Entity.Anken;
import com.example.WebApp.Entity.Department;
import com.example.WebApp.Repository.AnkenRepository;
import com.example.WebApp.Repository.DepartmentRepository;

@Controller
public class AnkenController {

    private final AnkenRepository ankenRepository;
    private final DepartmentRepository departmentRepository;

    public AnkenController(AnkenRepository ankenRepository, DepartmentRepository departmentRepository) {
        this.ankenRepository = ankenRepository;
        this.departmentRepository = departmentRepository;
    }

    // 案件一覧
    @GetMapping("/AK01")
    public String ankenListPage(Model model) {
        model.addAttribute("ankens", ankenRepository.findAll());
        return "AK01";
    }

    // 新規作成画面（AK02を作る前提）
    @GetMapping("/addAnken")
    public String addAnken(Model model) {
        model.addAttribute("anken", new Anken());
        model.addAttribute("departments", departmentRepository.findAll());
        return "AK02";
    }

    // 編集画面
    @GetMapping("/editAnken/{id}")
    public String editAnken(@PathVariable Long id, Model model) {
        Anken anken = ankenRepository.findById(id).orElse(null);
        model.addAttribute("anken", anken);
        model.addAttribute("departments", departmentRepository.findAll());
        return "AK02";
    }

    // 保存（新增/更新共用）
    @PostMapping("/saveAnken")
    public String saveAnken(@ModelAttribute Anken anken,
                            @RequestParam(required = false) Long departmentId) {

        // 你的 Anken 很可能是 ManyToOne Department，所以这里把 department 设进去
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId).orElse(null);
            anken.setDepartment(department);
        }

        ankenRepository.save(anken);
        return "redirect:/AK01";
    }

    // 删除
    @GetMapping("/deleteAnken/{id}")
    public String deleteAnken(@PathVariable Long id) {
        ankenRepository.deleteById(id);
        return "redirect:/AK01";
    }

    // 你原来的：根据部门ID取案件列表（给社員追加页面联动下拉用）
    @GetMapping("/anken/by-department")
    @ResponseBody
    public List<Anken> ankenList(@RequestParam Long departmentId) {
        return ankenRepository.findByDepartmentId(departmentId);
    }
}
