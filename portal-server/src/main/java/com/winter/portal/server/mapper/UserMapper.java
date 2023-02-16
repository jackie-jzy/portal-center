package com.winter.portal.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.winter.portal.api.query.UserQuery;
import com.winter.portal.server.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> page(IPage page, @Param("uq") UserQuery userQuery);

    Set<Long> getUserRoleIds(Serializable id);
}
