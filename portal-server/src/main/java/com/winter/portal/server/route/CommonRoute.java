package com.winter.portal.server.route;

import com.winter.portal.server.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 首页
 * </p>
 *
 * @author jzyan
 * @since 2023-01-30
 */
@Controller
public class CommonRoute {

    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    @RequestMapping(Constants.ROUTE_PREFIX + "/welcome")
    public String welcome() {
        return "/welcome";
    }

    @RequestMapping(Constants.ROUTE_PREFIX + "/login")
    public String login() {
        return "/login";
    }

}
