package com.winter.portal.server.route.admin;

import com.winter.portal.server.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author jzyan
 * @since 2023-01-30
 */
@Controller
@RequestMapping(Constants.ROUTE_ADMIN_PREFIX + "/resource/")
public class ResourceRoute {

    /**
     * 菜单列表
     *
     * @return
     */
    @RequestMapping("table")
    public String table() {
        return "page/admin/resource/table";
    }

}
