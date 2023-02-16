package com.winter.portal.server.constant;

/**
 * <p>
 *
 * </p>
 *
 * @author jzyan
 * @since 2022-04-29
 */
public interface Constants {

    String SEC_KEY = "b2cfebb3cd5f4f918d6029a21d4f1ca9";
    /**
     * 路由前缀
     **/
    String ROUTE_PREFIX = "route";
    /**
     * admin
     */
    String ADMIN_PREFIX = "admin";
    /**
     * api
     */
    String API_PREFIX = "api";
    String ROUTE_ADMIN_PREFIX = ROUTE_PREFIX + "/" + ADMIN_PREFIX;

}
