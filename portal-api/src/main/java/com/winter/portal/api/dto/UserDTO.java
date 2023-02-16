package com.winter.portal.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.jzyan.framework.core.annotation.valid.Phone;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统日志记录表
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @NotNull(message = "not null", groups = Update.class)
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("部门id")
    private Long orgId;
    @NotBlank(message = "not null")
    @Length(min = 6, max = 15, message = "账号6-15位")
    @ApiModelProperty(value = "用户账号", required = true)
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @NotBlank(message = "not null")
    @Length(max = 16, message = "不得超过16位")
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickname;
    @NotBlank(message = "not null")
    @Phone(message = "手机号格式错误")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @NotNull(message = "not null")
    @Max(value = 1, message = "0或1")
    @Min(value = 0, message = "0或1")
    @ApiModelProperty(value = "是否启用 1-是，0-否", required = true)
    private Integer enabled;
    @NotNull(message = "not null")
    @Max(value = 1, message = "0或1")
    @Min(value = 0, message = "0或1")
    @ApiModelProperty(value = "超级用户 1-是，0-否", required = true)
    private Integer root;

    public interface Update {
    }

}
