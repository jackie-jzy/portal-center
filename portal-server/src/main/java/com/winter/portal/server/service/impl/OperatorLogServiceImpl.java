package com.winter.portal.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winter.portal.api.query.OperatorLogQuery;
import com.winter.portal.api.vo.OperatorLogVO;
import com.winter.portal.server.entity.OperatorLogEntity;
import com.winter.portal.server.mapper.OperatorLogMapper;
import com.winter.portal.server.service.OperatorLogService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jzyan.framework.core.utils.ConvertUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志记录表 服务实现类
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */
@Service
public class OperatorLogServiceImpl extends ServiceImpl<OperatorLogMapper, OperatorLogEntity> implements OperatorLogService {

    @Override
    public IPage<OperatorLogVO> page(OperatorLogQuery operatorLogQuery) {
        IPage page = new Page<>(operatorLogQuery.getCurrent(), operatorLogQuery.getLimit());
        IPage<OperatorLogEntity> content = page(page, Wrappers.<OperatorLogEntity>lambdaQuery()
                .eq(StringUtils.isNotBlank(operatorLogQuery.getTitle())
                        , OperatorLogEntity::getTitle
                        , operatorLogQuery.getTitle())
                .eq(StringUtils.isNotBlank(operatorLogQuery.getMethod())
                        , OperatorLogEntity::getMethod
                        , operatorLogQuery.getMethod())
                .eq(StringUtils.isNotBlank(operatorLogQuery.getRequestUrl())
                        , OperatorLogEntity::getRequestUrl
                        , operatorLogQuery.getRequestUrl())
                .eq(StringUtils.isNotBlank(operatorLogQuery.getOperatorName())
                        , OperatorLogEntity::getOperatorName
                        , operatorLogQuery.getOperatorName())
                .eq(ObjectUtils.isNotEmpty(operatorLogQuery.getOperatorId())
                        , OperatorLogEntity::getOperatorId
                        , operatorLogQuery.getOperatorId())
                .eq(ObjectUtils.isNotEmpty(operatorLogQuery.getBusinessType())
                        , OperatorLogEntity::getBusinessType
                        , operatorLogQuery.getBusinessType())
                .eq(ObjectUtils.isNotEmpty(operatorLogQuery.getStatus())
                        , OperatorLogEntity::getStatus
                        , operatorLogQuery.getStatus())
                .eq(ObjectUtils.isNotEmpty(operatorLogQuery.getType())
                        , OperatorLogEntity::getType
                        , operatorLogQuery.getType())
        );
        page.setRecords(ConvertUtil.convert(content.getRecords(), OperatorLogVO.class));
        return page;
    }

    @Override
    public boolean clean() {
        return baseMapper.clean();
    }

}
