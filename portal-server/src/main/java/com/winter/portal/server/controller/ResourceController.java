package com.winter.portal.server.controller;

import com.winter.portal.api.vo.LeftMenuVO;
import com.winter.portal.api.vo.ResourceVO;
import com.winter.portal.server.constant.Constants;
import com.winter.portal.server.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jzyan.framework.core.response.CommonRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author jzyan
 * @since 2023-01-30
 */
@RestController
@RequestMapping(Constants.ADMIN_PREFIX + "/resource/")
@Api(value = "资源接口", tags = "资源管理")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 菜单列表
     *
     * @return
     */
    @GetMapping("table")
    @ApiOperation(value = "资源列表", tags = "资源管理")
    public CommonRes<List<ResourceVO>> table() {
        return CommonRes.success(resourceService.table());
    }

    /**
     * 左侧菜单
     *
     * @return
     */
    @GetMapping("/authorized/left/menu")
    @ApiOperation(value = "资源列表", tags = "资源管理")
    public CommonRes<LeftMenuVO> menuTree() {
        LeftMenuVO leftMenuVO = resourceService.menuTree();
        return CommonRes.success(leftMenuVO);
    }

}
