package com.winter.portal.api.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jzyan.framework.core.mobel.Page;

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
public class OperatorLogQuery extends Page {

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

    @ApiModelProperty("请求url")
    private String requestUrl;

    @ApiModelProperty("操作人")
    private String operatorName;

    @ApiModelProperty("操作人id")
    private Long operatorId;

}
