package com.winter.portal.server.enums;

import lombok.AllArgsConstructor;
import org.jzyan.framework.core.response.ResCode;

/**
 * @ProjectName : portal
 * @FileName : CodeEnum
 * @Version : 1.0
 * @Package : com.juzishuke.portal.enums
 * @Description :
 * @Author : jzyan
 * @CreateDate : 2020/09/21 10:20
 */
@AllArgsConstructor
public enum ErrorCodeEnum implements ResCode {

    /**
     * 1-哪个系统 501-系统级错误，502-业务级错误，000-候补位
     */

    // 业务级异常
    CODE_1402001(1402001, "用户名或密码错误"),
    CODE_1402002(1402002, "用户已停用"),
    CODE_1402003(1402003, "认证异常通用码"),
    // 业务级异常
    CODE_1502001(1502001, "注册账号已存在"),
    CODE_1502002(1502002, "原密码错误"),
    CODE_1502003(1502003, "账号为可用状态不可删除"),
    CODE_1502004(1502004, "系统角色已存在"),
    CODE_1502005(1502005, "角色为可用状态不可删除"),
    CODE_1502006(1502006, "系统资源已存在"),
    CODE_1502007(1502007, "系统资源Key已存在"),
    CODE_1502009(1502009, "部门名称已存在"),
    CODE_1502008(1502008, "资源为可用状态不可删除"),
    CODE_1502010(1502010, "部门编码已存在"),
    CODE_1502011(1502011, "部门有子集或正在使用中不可删除"),
    CODE_1502012(1502012, "当前类型下已存在字典数据不可删除"),
    CODE_1502013(1502013, "同一类型下的字典值不可重复"),
    CODE_1502014(1502014, "字典类型编码已存在"),

    // 系统级异常
    ;

    private long code;
    private String message;

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
