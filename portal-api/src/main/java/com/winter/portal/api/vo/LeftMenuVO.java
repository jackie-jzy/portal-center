package com.winter.portal.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 左侧菜单
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Data
public class LeftMenuVO implements Serializable {

    @ApiModelProperty("首页配置")
    private HomeInfo homeInfo;
    @ApiModelProperty("logo配置")
    private HomeInfo logoInfo;
    @ApiModelProperty("菜单tree")
    private List<MenuInfoVO> menuInfo;

    @Data
    public static class HomeInfo {
        @ApiModelProperty("标题")
        private String title;
        @ApiModelProperty("链接")
        private String href;
        @ApiModelProperty("图片地址")
        private String image;
    }

}
