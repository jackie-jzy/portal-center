package com.winter.portal.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单tree
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Data
public class MenuInfoVO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty("菜单名称")
    private String title;
    @ApiModelProperty("链接")
    private String href;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("目标")
    private String target;
    @ApiModelProperty("子元素")
    private List<MenuInfoVO> child;

}
