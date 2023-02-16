package com.winter.portal.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 菜单列表
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Data
public class ResourceVO implements Serializable {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("父级资源id")
    private Long parentId;
    @ApiModelProperty("资源名")
    private String resourceName;
    @ApiModelProperty("权限标识")
    private String permission;
    @ApiModelProperty("资源图标")
    private String icon;
    @ApiModelProperty("资源链接")
    private String url;
    @ApiModelProperty("资源类型 (1-目录, 2-菜单, 3-按钮)")
    private Integer type;
    @ApiModelProperty("同级资源排序字段")
    private Integer sort;

}
