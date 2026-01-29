package com.example.WebApp.Controller;

import org.springframework.stereotype.Controller;
// 引入 Model 类
// Model 用来把数据从 Controller 传到画面(html)
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 引入 Department 实体类（对应数据库表）
import com.example.WebApp.Entity.Department;

// 引入 Department 的 Repository（数据库操作用）
import com.example.WebApp.Repository.DepartmentRepository;

// 告诉 Spring：这个类是控制器
@Controller
public class DepartmentController {

    // 声明一个成员变量：部门用的数据库操作对象
    private final DepartmentRepository departmentRepository;

    // 构造方法（Spring 启动时自动帮忙传入 departmentRepository）
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // 部署一覧画面表示
    // 当浏览器访问 /BK01 时执行这个方法
    @GetMapping("/BK01")
    public String departmentList(Model model) {
        // 从数据库中取得所有部门数据
        model.addAttribute("departments", departmentRepository.findAll());
        // 返回 BK01.html 画面
        return "BK01";
    }

    // 新規作成画面表示
    // 访问 /addDepartment 时进入
    @GetMapping("/addDepartment")
    public String addDepartment(Model model) {
        // 创建一个空的 Department 对象, 相当于填入空白数据
        model.addAttribute("department", new Department());
        return "BK02";
    }

    // 編集画面表示
    // URL 例子： /editDepartment/3
    // {id} 会被自动取出来
    @GetMapping("/editDepartment/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        // 根据 id 从数据库查询部门
        Department department = departmentRepository.findById(id).orElse(null);
        // 把部门数据放进 model
        model.addAttribute("department", department);
        return "BK02";
    }

    // 保存
    // 当表单 POST 到 /saveDepartment 时进入
    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute Department department) {
        // 如果 department 里有 ID → 更新
        // 如果没有 ID → 新增
        // Spring Data JPA 自动判断
        departmentRepository.save(department);

        // 保存完成后回到一覧画面
        return "redirect:/BK01";
    }

    // 削除
    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        // 根据 ID 删除数据
        departmentRepository.deleteById(id);
        // 删除后回到一覧
        return "redirect:/BK01";
    }
}
