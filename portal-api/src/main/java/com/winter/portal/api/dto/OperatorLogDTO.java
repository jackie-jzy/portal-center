package com.winter.portal.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class OperatorLogDTO implements Serializable {

    @ApiModelProperty("模块系统")
    private String title;

    @ApiModelProperty("操作类别 0-其他，1-手机端用户，2-后端用户")
    private Integer type;

    @ApiModelProperty("操作状态 1-正常，0-异常")
    private Integer status;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("业务类型 0-其它,1-新增,2-修改,3-删除,4-导出,5-导入,6-对外接口")
    private Integer businessType;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("请求url")
    private String requestUrl;

    @ApiModelProperty("请求参数")
    private String requestParam;

    @ApiModelProperty("响应参数")
    private String responseParam;

    @ApiModelProperty("异常信息")
    private String errorMsg;

    @ApiModelProperty("操作人")
    private String operatorName;

    @ApiModelProperty("操作人id")
    private Long operatorId;

    @ApiModelProperty("操作人ip")
    private String operatorIp;

    @ApiModelProperty("操作人所属部门")
    private String operatorOrg;

}
