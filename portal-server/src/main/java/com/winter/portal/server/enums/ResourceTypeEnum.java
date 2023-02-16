package com.winter.portal.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资源类型 枚举常量
 *
 * @Author : jzyan
 * @CreateDate : 2020/09/21 10:30
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    /*资源类型*/
    CODE_1(1, "系统"),
    CODE_2(2, "菜单"),
    CODE_3(3, "API"),
    CODE_4(4, "公共资源"),
    ;

    private int code;
    private String message;

}
