package com.Benjamin.crud.service;

import com.Benjamin.crud.dao.DepartmentMapper;
import com.Benjamin.crud.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> getDept() {
        return departmentMapper.selectByExample(null);
    }
}
