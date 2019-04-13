package com.lindsay.test.dataobject;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 18:59
 * @Description:
 */
@Data
@Entity
@Table
@SQLDelete(sql = "update pf_user set status = 2 where id = ?")
@Where(clause = "status = 1")
public class PfUser {

    @Id
    private String userId;
    /**
     * 登陆账号
     */
    private String userCode;

    private String userName;
    /**
     * 用户密码Md5
     */
    private String password;
    /**
     * 性别 1-男 2-男
     */
    private Integer gender;
    /**
     * 联系方式
     */
    private String phoneNumber;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 0-未启用 1-启用 2-删除
     */
    private Integer status;
    /**
     * 部门Id
     */
    private String departmentId;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 最后一次登陆时间
     */
    private Timestamp lastLoginTime;
    /**
     * 最后一次登陆Ip
     */
    private String lastLoginIp;

}
