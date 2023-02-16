package com.winter.portal.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * rsa 公钥
 * </p>
 *
 * @author jzyan
 * @since 2023-02-10
 */
@Data
public class RsaPublicKeyVO implements Serializable {

    @ApiModelProperty("缓存key")
    private String key;
    @ApiModelProperty("公钥")
    private String publicKey;

}
