package com.winter.portal.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.winter.portal.api.dto.UserDTO;
import com.winter.portal.api.dto.UserEnabledDTO;
import com.winter.portal.api.query.UserQuery;
import com.winter.portal.api.vo.UserVO;
import com.winter.portal.server.entity.UserEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 获取账号信息根据登录账号
     *
     * @param username
     * @return
     */
    UserEntity getByUsername(String username);

    /**
     * 列表
     *
     * @param userQuery
     * @return
     */
    IPage<UserVO> table(UserQuery userQuery);

    /**
     * 更新状态
     *
     * @param user
     */
    void updateStatus(UserEnabledDTO user);

    /**
     * 添加
     *
     * @param user
     */
    void add(UserDTO user);

    /**
     * 获取用户角色id集合
     *
     * @param id
     * @return
     */
    Set<Long> getUserRoleIds(Serializable id);
}
