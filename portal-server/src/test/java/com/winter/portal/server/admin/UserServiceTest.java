package com.winter.portal.server.admin;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.winter.portal.api.query.UserQuery;
import com.winter.portal.api.vo.UserVO;
import com.winter.portal.server.PortalServerApplicationTests;
import com.winter.portal.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author jzyan
 * @since 2023-02-04
 */
public class UserServiceTest extends PortalServerApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void tableTest() {
        UserQuery query = new UserQuery();
        IPage<UserVO> page = userService.table(query);
        System.out.println(JSON.toJSONString(page));
    }

}
