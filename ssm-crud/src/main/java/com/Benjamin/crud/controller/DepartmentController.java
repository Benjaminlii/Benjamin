package com.Benjamin.crud.controller;

import com.Benjamin.crud.pojo.Department;
import com.Benjamin.crud.pojo.Message;
import com.Benjamin.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/getDepartments")
    @ResponseBody
    public Message getDept(){
        List<Department> departments = departmentService.getDept();
        return Message.success().add("depts", departments);
    }
}
