package com.example.WebApp.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebApp.Entity.Anken;
import com.example.WebApp.Entity.Employee;
import com.example.WebApp.Repository.AnkenRepository;
import com.example.WebApp.Repository.DepartmentRepository;
import com.example.WebApp.Repository.EmployeeRepository;
import com.example.WebApp.Util.Message;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AnkenRepository ankenRepository;

    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, AnkenRepository ankenRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.ankenRepository = ankenRepository;
    }

    @GetMapping("/SK01")
    public String employeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "SK01";
    }
    
    @GetMapping("/addEmployee")
    public String addUser(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("ankens", ankenRepository.findAll());
        
        return "SK02";
    }
    
    @GetMapping("/editEmployee/{id}")
    public String editUser(Model model, @PathVariable Long id) {
    	Employee employee = employeeRepository.findById(id).orElse(null);

    	if(employee != null) {
    		if(employee.getAnken() != null) {
    			employee.setDepartmentId(employee.getAnken().getDepartment().getDepartment_id());
    			employee.setAnkenId(employee.getAnken().getAnken_id());
    		}
    	}
    	
        model.addAttribute("employee", employee);
        
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("ankens", ankenRepository.findAll());
        return "SK02";
    }
    
    @PostMapping("/saveEmployee")
    public String save(@ModelAttribute Employee employee, Model model) {
    	
    	Optional<Employee> existedEmployee = employeeRepository.findByEmail(employee.getEmail());
    	
    	//メールアドレス重複チェック
    	if(existedEmployee.isPresent()) {
    		boolean isEdit = employee.getEmployee_id() != null;
    		
    		if(!isEdit || !existedEmployee.get().getEmployee_id().equals(employee.getEmployee_id())) {
    			model.addAttribute("errorMsg", Message.get("BE001"));
    			
    			model.addAttribute("employee", employee);
    			model.addAttribute("departments", departmentRepository.findAll());
    	        model.addAttribute("ankens", ankenRepository.findAll());
    	        
    	        return "SK02";
    		}
    	}
    	
    	
    	if(employee.getAnkenId() != null) {
    		Anken anken = ankenRepository.findById(employee.getAnkenId()).orElse(null);
    		employee.setAnken(anken);
    	}
    	
    	employeeRepository.save(employee);
    	
    	return "redirect:/SK01";
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteUser(@PathVariable Long id) {
    	employeeRepository.deleteById(id);
    	return "redirect:/SK01";
    }
    
}