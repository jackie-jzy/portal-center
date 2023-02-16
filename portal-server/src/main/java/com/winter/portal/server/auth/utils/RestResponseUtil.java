package com.winter.portal.server.auth.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
public class RestResponseUtil {


    public static void response(HttpServletResponse response, String param) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(param);
    }

}
