package com.zbcn.combootes.user.entity;

import lombok.Data;

/**
 *  用户信息
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/3 17:07
 */
@Data
public class UserInfo {

     private String address;
     private Integer age;
     private String birthDate;
     private Long createTime;
     private String name;
     private String remark;
     private String salary;
}
