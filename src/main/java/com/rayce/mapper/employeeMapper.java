package com.rayce.mapper;

import com.rayce.pojo.employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface employeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(employee record);

    employee selectByPrimaryKey(Integer id);

    List<employee> selectAll();

    int updateByPrimaryKey(employee record);
}