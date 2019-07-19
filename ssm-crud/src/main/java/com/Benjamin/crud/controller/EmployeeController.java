package com.Benjamin.crud.controller;

import com.Benjamin.crud.pojo.Employee;
import com.Benjamin.crud.pojo.Message;
import com.Benjamin.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理关于员工的请求
 * author:Benjamin
 * date:2019.6.28
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 分页查询所有员工信息
     * @param page 查询的页码
     * @return 结果集
     */
    @RequestMapping(value = "/getEmployeesByPage")
    @ResponseBody
    public Message getEmployeesByPage(@RequestParam(value = "page", defaultValue = "1") int page) {
        //引入PageHelper分页插件
        //只需要调用，传入页码和每页的数据量
        PageHelper.startPage(page, 5);
        //startPage后紧跟的查询就会根据设置好的参数去查询
        List<Employee> employees = employeeService.getEmployees();

        //使用PageInfo包装查询结果，参数为查询结果，当前页面显示分页的页数
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        return Message.success().add("pageinfo", pageInfo);
    }

    @RequestMapping(value = "/getEmployee")
    @ResponseBody
    public Message getEmployee(@RequestParam Integer empId){
        Employee employee = employeeService.getEmployee(empId);
        return Message.success().add("emp", employee);
    }

    @RequestMapping("/addEmployee")
    @ResponseBody
    public Message addEmployee(@Valid Employee employee, BindingResult bindingResult){
        //先进行数据的校验，根据僬侥结果判断是否进行业务逻辑
        if(bindingResult.hasErrors()){
            //map用于储存错误信息返回给前端
            Map<String, String>map = new HashMap<>();
            //得到错误的结果集
            List<FieldError> errors = bindingResult.getFieldErrors();
            //便利错误结果集，存储在map中
            for(FieldError fieldError : errors){
                //getField得到某一个错误的字段名
                System.out.println("--->错误字段名" + fieldError.getField());
                //getDefaultMessage返回定义的错误信息
                System.out.println("--->错误信息" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.error().add("errorField", map);
        }else{
            employeeService.addEmployee(employee);
            return Message.success();
        }
    }

    @RequestMapping("/updateEmployee")
    @ResponseBody
    public Message updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
        return Message.success();
    }

    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public Message deleteEmployee(int empId){
        employeeService.deleteEmployee(empId);
        return Message.success();
    }

    @RequestMapping("/deleteEmployees")
    @ResponseBody
    public Message deleteEmployees(String idS){
        if(idS.contains("-")){
            String[] idArray = idS.split("-");
            List<Integer> ids = new ArrayList<>();
            for(String id : idArray) {
                ids.add(Integer.parseInt(id));
            }
            employeeService.deleteEmployees(ids);
        }else{
            employeeService.deleteEmployee(Integer.parseInt("ids"));
        }
        return Message.success();
    }
}
