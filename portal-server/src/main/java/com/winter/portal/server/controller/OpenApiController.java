package com.winter.portal.server.controller;


import com.winter.portal.api.vo.RsaPublicKeyVO;
import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import com.winter.portal.server.constant.Constants;
import com.winter.portal.server.entity.UserEntity;
import com.winter.portal.server.enums.ErrorCodeEnum;
import com.winter.portal.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.RSAEncrypUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 公开接口 前端控制器
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Validated
@RestController
@RequestMapping(Constants.API_PREFIX + "/")
@Api(value = "公开接口", tags = "公开接口管理")
public class OpenApiController {
    @Autowired
    private AuthorizationCacheService cacheService;
    @Autowired
    private UserService userService;

    @GetMapping("rsa/public-key")
    @ApiOperation(value = "获取RSA公钥", tags = "公开接口管理")
    public CommonRes<RsaPublicKeyVO> getRsaPublicKey(@RequestParam String username) {
        UserEntity userEntity = userService.getByUsername(username);
        if (ObjectUtils.isEmpty(userEntity)) {
            return CommonRes.failed(ErrorCodeEnum.CODE_1402001);
        }
        Map<String, String> map = RSAEncrypUtil.initRSAKey(2048);
        String privateKey = map.get("privateKey");
        String publicKey = map.get("publicKey");
        cacheService.putRsaKey(username, privateKey);
        RsaPublicKeyVO rsaPublicKey = new RsaPublicKeyVO();
        rsaPublicKey.setKey(username);
        rsaPublicKey.setPublicKey(publicKey);
        return CommonRes.success(rsaPublicKey);
    }

}

