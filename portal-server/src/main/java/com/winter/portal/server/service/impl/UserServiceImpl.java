package com.winter.portal.server.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winter.portal.api.dto.UserDTO;
import com.winter.portal.api.dto.UserEnabledDTO;
import com.winter.portal.api.query.UserQuery;
import com.winter.portal.api.vo.UserVO;
import com.winter.portal.server.auth.AuthUser;
import com.winter.portal.server.auth.service.SessionService;
import com.winter.portal.server.entity.UserEntity;
import com.winter.portal.server.enums.ErrorCodeEnum;
import com.winter.portal.server.mapper.UserMapper;
import com.winter.portal.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.exception.BusinessException;
import org.jzyan.framework.core.utils.ConvertUtil;
import org.jzyan.framework.core.utils.PasswordBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService, UserDetailsService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取账号信息根据登录账号
     *
     * @param username
     * @return
     */
    @Override
    public UserEntity getByUsername(String username) {
        return getOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername, username));
    }

    /**
     * 列表
     *
     * @param userQuery
     * @return
     */
    @Override
    public IPage<UserVO> table(UserQuery userQuery) {
        IPage page = new Page<>(userQuery.getCurrent(), userQuery.getLimit());
        if (userQuery.getStartDate() == null || userQuery.getEndDate() == null) {
            userQuery.setStartDate(LocalDateTimeUtil.beginOfDay(LocalDateTime.now()));
            userQuery.setEndDate(LocalDateTimeUtil.endOfDay(LocalDateTime.now()));
        }
        List<UserEntity> content = baseMapper.page(page, userQuery);
        return page.setRecords(ConvertUtil.convert(content, UserVO.class));
    }

    /**
     * 更新状态
     *
     * @param user
     */
    @Override
    public void updateStatus(UserEnabledDTO user) {
        UserEntity userEntity = ConvertUtil.convert(user, UserEntity.class);
        userEntity.setUpdateUser(sessionService.getUsername());
        updateById(userEntity);
        // 清理session
        userEntity = getById(userEntity.getId());
        sessionService.clearSession(userEntity.getUsername());
    }

    /**
     * 添加
     *
     * @param user
     */
    @Override
    public void add(UserDTO user) {
        UserEntity userEntity = getByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(userEntity)) {
            throw new BusinessException(ErrorCodeEnum.CODE_1502001);
        }
        userEntity = ConvertUtil.convert(user, UserEntity.class);
        String password = PasswordBuilderUtil.createPassword(8);
        userEntity.setPassword(passwordEncoder.encode(password));
        log.info("用户:{}, 密码:{}", userEntity.getUsername(), password);
        save(userEntity);
    }

    /**
     * 获取用户角色id集合
     *
     * @param id
     * @return
     */
    @Override
    public Set<Long> getUserRoleIds(Serializable id) {
        return baseMapper.getUserRoleIds(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户
        UserEntity userEntity = this.getByUsername(username);
        if (ObjectUtils.isEmpty(userEntity)) {
            throw new UsernameNotFoundException("未知用户:" + username);
        }
        // 获取权限
        Set<Long> roleIds = this.getUserRoleIds(userEntity.getId());
        // 封装user
        AuthUser user = new AuthUser(userEntity.getUsername(), userEntity.getPassword()
                , userEntity.getEnabled() == 1 ? true : false, true
                , true, true, new ArrayList());
        user.setNickname(userEntity.getNickname());
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setFirstLogin(userEntity.getFirstLogin());
        user.setPhone(userEntity.getPhone());
        user.setRoot(userEntity.getRoot());
        user.setRoleIds(roleIds);
        return user;
    }
}
