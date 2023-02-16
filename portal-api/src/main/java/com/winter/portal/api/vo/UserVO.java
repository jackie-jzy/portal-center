package com.winter.portal.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author jzyan
 * @since 2023-02-04
 */
@Data
public class UserVO implements Serializable {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("部门")
    private String orgName;
    @ApiModelProperty("角色")
    private String roleName;
    @ApiModelProperty("用户账号")
    private String username;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("否启用 1-是，0-否")
    private Integer enabled;
    @ApiModelProperty("超级用户 1-是，0-否")
    private Integer root;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
