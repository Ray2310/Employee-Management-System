package com.rayce.mapper;

import com.rayce.pojo.dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface deptMapper {

    int deleteByPrimaryKey(Integer deptId);

    int insert(dept record);

    dept selectByPrimaryKey(Integer deptId);

    List<dept> selectAll();

    int updateByPrimaryKey(dept record);
}