package com.winter.portal.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.winter.portal.api.dto.UserDTO;
import com.winter.portal.api.dto.UserEnabledDTO;
import com.winter.portal.api.query.UserQuery;
import com.winter.portal.api.vo.UserVO;
import com.winter.portal.server.auth.service.SessionService;
import com.winter.portal.server.constant.Constants;
import com.winter.portal.server.entity.UserEntity;
import com.winter.portal.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Validated
@RestController
@RequestMapping(Constants.ADMIN_PREFIX + "/user")
@Api(value = "用户接口", tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("table")
    @ApiOperation(value = "用户列表", tags = "用户管理")
    public CommonRes<IPage<UserVO>> table(UserQuery userQuery) {
        return CommonRes.success(userService.table(userQuery));
    }

    @GetMapping
    @ApiOperation(value = "用户详情", tags = "用户管理")
    public CommonRes<UserVO> detail(@RequestParam Serializable id) {
        UserEntity userEntity = userService.getById(id);
        return CommonRes.success(ConvertUtil.convert(userEntity, UserVO.class));
    }

    @PostMapping
    @ApiOperation(value = "添加用户", tags = "用户管理")
    public CommonRes add(@RequestBody @Valid UserDTO user) {
        userService.add(user);
        return CommonRes.success();
    }

    @PutMapping
    @ApiOperation(value = "更新用户", tags = "用户管理")
    public CommonRes update(@RequestBody @Validated(value = {Default.class, UserDTO.Update.class}) UserDTO user) {
        UserEntity userEntity = ConvertUtil.convert(user, UserEntity.class);
        userEntity.setUpdateUser(sessionService.getUsername());
        userService.updateById(userEntity);
        return CommonRes.success();
    }

    @PutMapping("/enabled")
    @ApiOperation(value = "更新用户", tags = "用户管理")
    public CommonRes updateStatus(@RequestBody @Valid UserEnabledDTO user) {
        userService.updateStatus(user);
        return CommonRes.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除用户", tags = "用户管理")
    public CommonRes delete(Serializable id) {
        userService.removeById(id);
        return CommonRes.success();
    }

}

