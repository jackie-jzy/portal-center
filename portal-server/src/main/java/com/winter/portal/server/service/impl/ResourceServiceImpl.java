package com.winter.portal.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winter.portal.api.vo.LeftMenuVO;
import com.winter.portal.api.vo.MenuInfoVO;
import com.winter.portal.api.vo.ResourceVO;
import com.winter.portal.server.auth.service.SessionService;
import com.winter.portal.server.entity.ResourceEntity;
import com.winter.portal.server.mapper.ResourceMapper;
import com.winter.portal.server.service.ResourceService;
import org.jzyan.framework.core.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements ResourceService {
    @Autowired
    private SessionService sessionService;

    /**
     * 资源列表
     *
     * @return
     */
    @Override
    public List<ResourceVO> table() {
        List<ResourceEntity> list = list();
        return ConvertUtil.convert(list, ResourceVO.class);
    }

    /**
     * 左侧菜单
     *
     * @return
     */
    @Override
    public LeftMenuVO menuTree() {
        LeftMenuVO leftMenuVO = new LeftMenuVO();
        LeftMenuVO.HomeInfo homeInfo = new LeftMenuVO.HomeInfo();
        homeInfo.setTitle("首页");
        homeInfo.setHref("route/welcome");
        leftMenuVO.setHomeInfo(homeInfo);
        LeftMenuVO.HomeInfo logoInfo = new LeftMenuVO.HomeInfo();
        logoInfo.setTitle("LAYUI MINI");
        logoInfo.setImage("images/logo.png");
        leftMenuVO.setLogoInfo(logoInfo);
        Map<String, Object> map = new HashMap<>(2);
        map.put("isRoot", sessionService.getUser().getRoot());
        if (!sessionService.isRoot() && CollectionUtils.isEmpty(sessionService.getRoleIds())) {
            map.put("roleIds", -1);
        } else {
            map.put("roleIds", sessionService.getRoleIds());
        }
        List<ResourceEntity> list = baseMapper.leftMenu(map);
        List<MenuInfoVO> menuInfoList = new ArrayList<>();
        for (ResourceEntity result : list) {
            if (result.getParentId() == 0) {
                //调用方法给子类添加数据
                MenuInfoVO menuInfo = new MenuInfoVO();
                menuInfo.setId(result.getId());
                menuInfo.setTitle(result.getResourceName());
                menuInfo.setHref(result.getUrl());
                menuInfo.setIcon(result.getIcon());
                menuInfo.setTarget("_self");
                menuInfoList.add(getMenuTree(menuInfo, list));
            }
        }
        MenuInfoVO baseMenuInfo = new MenuInfoVO();
        baseMenuInfo.setTitle("菜单");
        baseMenuInfo.setHref("");
        baseMenuInfo.setIcon("fa fa-address-book");
        baseMenuInfo.setTarget("_self");
        baseMenuInfo.setChild(menuInfoList);
        List<MenuInfoVO> baseMenuInfoList = new ArrayList<>();
        baseMenuInfoList.add(baseMenuInfo);
        leftMenuVO.setMenuInfo(baseMenuInfoList);
        return leftMenuVO;
    }

    /**
     * 递归获取菜单Tree
     *
     * @param result
     * @param list
     * @return
     */
    private MenuInfoVO getMenuTree(MenuInfoVO result, List<ResourceEntity> list) {
        for (ResourceEntity resourceEntity : list) {
            //如果父类主键等于传过来实体类的ID
            if (resourceEntity.getParentId().equals(result.getId())) {
                MenuInfoVO menuInfo = new MenuInfoVO();
                menuInfo.setId(resourceEntity.getId());
                menuInfo.setTitle(resourceEntity.getResourceName());
                menuInfo.setHref(resourceEntity.getUrl());
                menuInfo.setIcon(resourceEntity.getIcon());
                menuInfo.setTarget("_self");
                if (result.getChild() == null) {
                    result.setChild(new ArrayList<>());
                }
                // 递归调用
                result.getChild().add(getMenuTree(menuInfo, list));
            }
        }
        return result;
    }

}
