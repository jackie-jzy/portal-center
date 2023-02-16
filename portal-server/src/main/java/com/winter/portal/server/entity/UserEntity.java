package com.winter.portal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Getter
@Setter
@TableName("sys_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 部门id
     */
    private Long orgId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后一次登陆ip
     */
    private String lastLoginIp;

    /**
     * 重试次数, 超过次数上限锁定账号
     */
    private Integer retiesTimes;

    /**
     * 是否启用 1-是，0-否
     */
    private Integer enabled;

    /**
     * 超级用户 1-是，0-否
     */
    private Integer root;

    /**
     * 首次登录 1-是，0-否
     */
    private Integer firstLogin;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
