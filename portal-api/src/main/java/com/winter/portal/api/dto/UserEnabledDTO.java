package com.winter.portal.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class UserEnabledDTO implements Serializable {

    @NotNull(message = "not null")
    @ApiModelProperty("id")
    private Long id;
    @NotNull(message = "not null")
    @Max(value = 1, message = "0或1")
    @Min(value = 0, message = "0或1")
    @ApiModelProperty("是否启用 1-是，0-否")
    private Integer enabled;

}
