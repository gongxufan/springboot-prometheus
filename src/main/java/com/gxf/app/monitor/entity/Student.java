package com.gxf.app.monitor.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_test_student") //对应表名
public class Student implements Serializable {

    //对应id，可不填
    @TableId
    private Integer id;

    //对应字段名，如果属性名和字段名一致，可不填
    @TableField("name")
    private String name;

    private String school;

    private String city;

    //表示表中没有这个字段，如果不加该注释，会抛异常
    @TableField(exist = false)
    private String address;
}