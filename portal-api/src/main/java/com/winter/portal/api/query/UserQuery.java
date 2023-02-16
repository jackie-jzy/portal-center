package com.winter.portal.api.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jzyan.framework.core.mobel.Page;

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
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends Page {
    @ApiModelProperty("用户账号")
    private String username;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("否启用 1-是，0-否")
    private Integer enabled;
    @ApiModelProperty("开始时间")
    private LocalDateTime startDate;
    @ApiModelProperty("结束时间")
    private LocalDateTime endDate;

}
