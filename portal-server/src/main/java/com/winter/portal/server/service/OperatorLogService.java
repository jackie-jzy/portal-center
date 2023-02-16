package com.winter.portal.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.winter.portal.api.query.OperatorLogQuery;
import com.winter.portal.api.vo.OperatorLogVO;
import com.winter.portal.server.entity.OperatorLogEntity;

/**
 * <p>
 * 系统日志记录表 服务类
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */
public interface OperatorLogService extends IService<OperatorLogEntity> {

    IPage<OperatorLogVO> page(OperatorLogQuery operatorLogQuery);

    boolean clean();
}
