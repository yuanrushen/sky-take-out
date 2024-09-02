package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "员工分页查询")
public class EmployeePageQueryDTO implements Serializable {

    @ApiModelProperty(value = "员工姓名")
    //员工姓名
    private String name;
    @ApiModelProperty(value = "页数")
    //页码
    private int page;
    @ApiModelProperty(value = "每页显示记录数")
    //每页显示记录数
    private int pageSize;

}
