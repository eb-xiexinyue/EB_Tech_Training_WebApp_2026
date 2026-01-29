package com.example.WebApp.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebApp.Entity.Department;
import com.example.WebApp.Repository.DepartmentRepository;
import com.example.WebApp.Util.Message;

@Controller
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // 一覧
    @GetMapping("/BK01")
    public String departmentList(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "BK01";
    }

    // 新規画面
    @GetMapping("/BK02")
    public String departmentAdd(Model model) {
        model.addAttribute("department", new Department());
        return "BK02";
    }

    // 編集画面
    @GetMapping("/BK02/{id}")
    public String departmentEdit(@PathVariable("id") Long id, Model model) {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isEmpty()) return "redirect:/BK01";

        model.addAttribute("department", dept.get());
        return "BK02";
    }

    // 保存（重複チェック付き）
    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute Department department, Model model) {

        Optional<Department> existed =
                departmentRepository.findByDepartmentName(department.getDepartment_name());

        if (existed.isPresent()) {
            boolean isEdit = department.getDepartment_id() != null;

            if (!isEdit || !existed.get().getDepartment_id().equals(department.getDepartment_id())) {
                model.addAttribute("errorMsg", Message.get("BE002"));
                model.addAttribute("department", department);
                return "BK02";
            }
        }

        departmentRepository.save(department);
        return "redirect:/BK01";
    }

    // 削除
    @PostMapping("/BK01/delete/{id}")
    public String departmentDelete(@PathVariable("id") Long id) {
        departmentRepository.deleteById(id);
        return "redirect:/BK01";
    }
}
