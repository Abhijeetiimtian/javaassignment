package com.citi.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citi.project.model.Employee;
import com.citi.project.service.EmployeeService;



@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/index")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }
    
    @GetMapping("/")
    public String viewLoginPage(Model model) {
       // model.addAttribute("listEmployees", employeeService.getAllEmployees());
    	
        return "login";
    }


    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    
    @PostMapping("/loginEmp")
    public String loginEmployee(@RequestParam("email")String email, @RequestParam("password")String password,RedirectAttributes att, Model model) {
        // save employee to database
        Employee emp =  employeeService.getEmployeeForLogin(email,password);
        if(emp != null) {
        	return "redirect:/index";
        }else {
        	String msg = "Invalid user or password";
        	 att.addFlashAttribute("msg", msg) ;
        	 return "redirect:/";
        }
       
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }
}
