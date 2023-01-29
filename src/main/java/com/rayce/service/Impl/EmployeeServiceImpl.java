package com.rayce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rayce.mapper.deptMapper;
import com.rayce.mapper.employeeMapper;
import com.rayce.pojo.dept;
import com.rayce.pojo.employee;
import com.rayce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private employeeMapper EmpMapper;
    @Autowired
    private deptMapper DeptMapper;

    @Override
    public Collection<employee> selectAll() {
        Collection<employee> list = EmpMapper.selectAll();
        //建立多表查询的关联关系
        for(employee li : list){
            dept dept = DeptMapper.selectByPrimaryKey(li.getDeptId());
            li.setDep(dept);
        }
        return list;
    }

    @Override
    public int insert(employee emp) {
        //需要将部门的信息传入
        //传入部门的id，然后将用户保存
        dept dept = DeptMapper.selectByPrimaryKey(emp.getDeptId());
        emp.setDep(dept);
        EmpMapper.insert(emp);
        return 1;
    }

    @Override
    public Collection<dept> getAllDept() {
        List<dept> depts = DeptMapper.selectAll();
        return depts;
    }

    @Override
    public int deleteById(Integer id) {
        int i = EmpMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public employee updateBefore(Integer id) {
        employee recard = EmpMapper.selectByPrimaryKey(id);
        return recard;
    }

    @Override
    public int updateAfter(employee emp) {
        int update = EmpMapper.updateByPrimaryKey(emp);
        return update;
    }

    @Override
    public PageInfo<employee> Page(Integer pageNum) {
        //开启分页功能，每页显示4条数据
        PageHelper.startPage(pageNum,8);
        List<employee> list = EmpMapper.selectAll();
        for(employee li : list){
            dept dept = DeptMapper.selectByPrimaryKey(li.getDeptId());
            li.setDep(dept);
        }
        //navigatePages 的作用 位置
        PageInfo<employee> pageInfo = new PageInfo<>(list,4);
        return pageInfo;
    }
}
