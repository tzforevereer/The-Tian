package com.tian.controller;


import com.tian.dao.DepartmentDao;
import com.tian.dao.EmployeeDao;
import com.tian.pojo.Department;
import com.tian.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {


    @Autowired
    EmployeeDao employeeDao;


    @Autowired
    DepartmentDao departmentDao;



    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "list";
    }



    @GetMapping("/emp")
    public String toAddPage(Model model){

        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments",departments);

        return "add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println(employee.toString());
        employeeDao.save(employee);//保存员工的信息

        //添加的操作
        return "redirect:/emps";
    }



    //员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        //查出原来的数据
        Employee employeeById = employeeDao.getEmployeeById(id);

        model.addAttribute("emp",employeeById);

        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments",departments);

        return "update";
    }



    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }




    //删除员工
    @GetMapping("/delemp/{id}")
    public  String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);

        return "redirect:/emps";
    }




}
