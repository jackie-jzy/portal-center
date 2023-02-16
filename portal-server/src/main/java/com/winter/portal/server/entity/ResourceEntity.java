package com.winter.portal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author jzyan
 * @since 2023-02-07
 */
@Getter
@Setter
@TableName("sys_resource")
public class ResourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级资源id, 系统则填0
     */
    private Long parentId;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 资源链接
     */
    private String url;

    /**
     * 资源类型 (1-目录, 2-菜单, 3-按钮)
     */
    private Integer type;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 同级资源排序字段
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
