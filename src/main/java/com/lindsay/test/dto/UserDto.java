package com.lindsay.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/27 09:30
 * @Description:
 */
@Data
public class UserDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_code")
    private String userCode;

    @JsonProperty("user_name")
    private String userName;

    private String password;

    private Integer gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    private Integer status;

    @JsonProperty("department_id")
    private String departmentId;

    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("create_time")
    private Timestamp createTime;

    private String creator;

}
