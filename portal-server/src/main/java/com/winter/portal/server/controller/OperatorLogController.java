package com.winter.portal.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.winter.portal.api.dto.OperatorLogDTO;
import com.winter.portal.api.query.OperatorLogQuery;
import com.winter.portal.api.vo.OperatorLogVO;
import com.winter.portal.server.entity.OperatorLogEntity;
import com.winter.portal.server.service.OperatorLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 * 系统日志记录表 前端控制器
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/sys/operator/log")
@Api(value = "系统日志接口", tags = "系统日志管理")
public class OperatorLogController {

    @Autowired
    private OperatorLogService operatorLogService;

    @PostMapping
    @ApiOperation(value = "新增日志", tags = "系统日志管理", notes = "新增日志")
    public CommonRes<Boolean> save(@RequestBody OperatorLogDTO operatorLogDTO) {
        return CommonRes.success(operatorLogService.save(ConvertUtil.convert(operatorLogDTO, OperatorLogEntity.class)));
    }

    @GetMapping("/page")
    @ApiOperation(value = "日志分页", tags = "系统日志管理", notes = "日志分页")
    public CommonRes<IPage<OperatorLogVO>> page(OperatorLogQuery operatorLogQuery) {
        return CommonRes.success(operatorLogService.page(operatorLogQuery));
    }

    @DeleteMapping
    @ApiOperation(value = "删除日志", tags = "系统日志管理", notes = "删除日志")
    public CommonRes<Boolean> removeBatchByIds(@RequestParam Long[] ids) {
        return CommonRes.success(operatorLogService.removeBatchByIds(Arrays.asList(ids)));
    }

    @DeleteMapping("/clean")
    @ApiOperation(value = "清空日志", tags = "系统日志管理", notes = "清空日志")
    public CommonRes clean() {
        operatorLogService.clean();
        return CommonRes.success();
    }

    @PostMapping("/export")
    @ApiOperation(value = "导出日志", tags = "系统日志管理", notes = "导出日志")
    public void export(HttpServletResponse response, OperatorLogQuery operatorLogQuery) {
    }

}

