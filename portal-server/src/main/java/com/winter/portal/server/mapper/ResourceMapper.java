package com.winter.portal.server.mapper;

import com.winter.portal.server.entity.ResourceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Repository
public interface ResourceMapper extends BaseMapper<ResourceEntity> {

    /**
     * 获取左侧菜单
     *
     * @param map
     * @return
     */
    List<ResourceEntity> leftMenu(Map<String, Object> map);
}
