package com.winter.portal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winter.portal.server.entity.OperatorLogEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统日志记录表 Mapper 接口
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */

@Repository
public interface OperatorLogMapper extends BaseMapper<OperatorLogEntity> {

    boolean clean();
}
