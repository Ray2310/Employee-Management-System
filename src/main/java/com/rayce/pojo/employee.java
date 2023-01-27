package com.rayce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private dept dep;
    private Integer deptId;
    private Date birth;

}