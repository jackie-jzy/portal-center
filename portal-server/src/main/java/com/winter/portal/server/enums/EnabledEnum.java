package com.winter.portal.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否启用 枚举常量
 *
 * @Author : jzyan
 * @CreateDate : 2020/09/21 10:30
 */
@Getter
@AllArgsConstructor
public enum EnabledEnum {

    /*是否启用*/
    CODE_1(1, "是"),
    CODE_0(0, "否"),
    ;

    private int code;
    private String message;

}
