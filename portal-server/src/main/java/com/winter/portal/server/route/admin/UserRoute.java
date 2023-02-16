package com.winter.portal.server.route.admin;

import com.winter.portal.server.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author jzyan
 * @since 2023-01-30
 */
@Controller
@RequestMapping(Constants.ROUTE_ADMIN_PREFIX + "/user/")
public class UserRoute {

    /**
     * 用户设置界面
     *
     * @return
     */
    @RequestMapping("set")
    public String set() {
        return "page/admin/user/setting";
    }

    /**
     * 重置密码界面
     *
     * @return
     */
    @RequestMapping("reset/pwd")
    public String resetPassword() {
        return "page/admin/user/reset-password";
    }

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping("table")
    public String table() {
        return "page/admin/user/table";
    }

    /**
     * 用户添加
     *
     * @return
     */
    @RequestMapping("add")
    public String add() {
        return "page/admin/user/add";
    }

    /**
     * 用户编辑
     *
     * @return
     */
    @RequestMapping("edit")
    public String edit(@RequestParam Serializable id, ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        return "page/admin/user/edit";
    }

}
