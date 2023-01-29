package com.rayce.service;

import com.github.pagehelper.PageInfo;
import com.rayce.pojo.dept;
import com.rayce.pojo.employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Collection<employee> selectAll();
    int insert(employee emp);
    Collection<dept> getAllDept();
    int deleteById(Integer id);
    employee updateBefore(Integer id);
    int updateAfter(employee emp);

    PageInfo<employee> Page(Integer pageNum);
}
