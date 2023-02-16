package com.winter.portal.server.service;

import com.winter.portal.api.vo.LeftMenuVO;
import com.winter.portal.api.vo.ResourceVO;
import com.winter.portal.server.entity.ResourceEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统资源表 服务类
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
public interface ResourceService extends IService<ResourceEntity> {

    /**
     * 资源列表
     *
     * @return
     */
    List<ResourceVO> table();

    /**
     * 左侧菜单
     *
     * @return
     */
    LeftMenuVO menuTree();
}
