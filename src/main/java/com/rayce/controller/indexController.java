package com.rayce.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rayce.mapper.deptMapper;
import com.rayce.mapper.employeeMapper;
import com.rayce.pojo.dept;
import com.rayce.pojo.employee;
import com.rayce.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private EmployeeService employeeService;
    public static int LastPage = 1; //用于记录最后一页
    public static int ThisPageNum = 1; //用于记录当前页

    @RequestMapping("/list")
    public String list(Model model){
        Collection<employee> list = employeeService.selectAll();
        model.addAttribute("list",list);
        return "list";
    }
    @GetMapping("/to/add")
    public String addEmp(Model model){
        Collection<dept> departments = employeeService.getAllDept();
        model.addAttribute("departments",departments);
        return "addEmp";
    }
    @PostMapping("/save")
    public String saveEmp(employee emp){
        employeeService.insert(emp);
        List<employee> list = (List<employee>) employeeService.selectAll();

        System.out.println("lastPAge=="+ LastPage);
        return "redirect:/list/"+LastPage;
    }

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
        return "redirect:/list/" + ThisPageNum;
    }
    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeService.deleteById(id);
        System.out.println(ThisPageNum);
        return "redirect:/list/" + ThisPageNum;
    }
    @RequestMapping("/list/{pageNum}")
    public String pageStart(@PathVariable("pageNum") Integer pageNum, Model model){

        PageInfo<employee> page = employeeService.Page(pageNum);
        List<employee> list = page.getList();
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        ThisPageNum = page.getPageNum();

        LastPage = page.getPages();
        return "list";
    }
}
