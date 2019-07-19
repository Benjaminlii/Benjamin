package com.Benjamin.crud.service;

import com.Benjamin.crud.dao.EmployeeMapper;
import com.Benjamin.crud.pojo.Employee;
import com.Benjamin.crud.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getEmployees() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public Employee getEmployee(int id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public void addEmployee(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteEmployee(int empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    public void deleteEmployees(List<Integer> ids) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}
