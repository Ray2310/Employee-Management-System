package com.rayce.controller;


import com.rayce.mapper.deptMapper;
import com.rayce.mapper.employeeMapper;
import com.rayce.pojo.dept;
import com.rayce.pojo.employee;
import com.rayce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class indexController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public String list(Model model){
        Collection<employee> list = employeeService.selectAll();
        model.addAttribute("list",list);
        return "list";
    }
    @GetMapping("/to/add")
    public String addEmp(Model model){
        Collection<dept> departments = employeeService.getAllDept();
        //        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "addEmp";
    }
    @PostMapping("/save")
    public String saveEmp(employee emp){
        employeeService.insert(emp);
        return "redirect:/list";
    }
//
    @RequestMapping("/updateEmp/{id}")
    public String update(Model model,@PathVariable("id") Integer id){

        employee emp = employeeService.updateBefore(id);
        model.addAttribute("emp",emp);
        Collection<dept> departments = employeeService.getAllDept();
        model.addAttribute("departments",departments);
        return "/updateEmp";
    }

    @PostMapping("/updateSucc")
    public String updateSuccess(employee emp){
        employeeService.updateAfter(emp);
        return "redirect:/list";
    }
    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeService.deleteById(id);
        return "redirect:/list";
    }
}
