package com.wujunshen.controller;

import com.wujunshen.entity.User;
import com.wujunshen.exception.ResultStatusCode;
import com.wujunshen.security.AccessToken;
import com.wujunshen.security.Audience;
import com.wujunshen.security.LoginParameter;
import com.wujunshen.service.UserService;
import com.wujunshen.util.JwtUtil;
import com.wujunshen.util.MD5Util;
import com.wujunshen.vo.BaseResultVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:16:47 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
@RestController
@Api(value = "/", description = "有关Java Web Token的操作")
public class JWTController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private Audience audience;

    @RequestMapping(value = "api/oauth/token", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取access_token", httpMethod = "POST",
            notes = "成功返回access_token",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Object getAccessToken(@ApiParam(value = "登录信息", required = true) @RequestBody LoginParameter loginParameter) {
        BaseResultVo baseResultVo = new BaseResultVo();

        //验证clientID
        if (loginParameter.getClientId() == null || (loginParameter.getClientId().compareTo(audience.getClientId()) != 0)) {
            baseResultVo.setCode(ResultStatusCode.INVALID_CLIENT_ID.getCode());
            baseResultVo.setMessage(ResultStatusCode.INVALID_CLIENT_ID.getMessage());
            return baseResultVo;
        }

        //验证用户名密码
        User user = userService.findUserInfoByName(loginParameter.getUserName());
        if (user == null) {
            baseResultVo.setCode(ResultStatusCode.INVALID_USER_NAME.getCode());
            baseResultVo.setMessage(ResultStatusCode.INVALID_USER_NAME.getMessage());

            return baseResultVo;
        } else {
            String md5Password = MD5Util.getMD5(loginParameter.getPassword() + user.getSalt());
            LOGGER.info("md5Password is: " + md5Password);
            if (md5Password.compareTo(user.getPassword()) != 0) {
                baseResultVo.setCode(ResultStatusCode.INVALID_PASSWORD.getCode());
                baseResultVo.setMessage(ResultStatusCode.INVALID_PASSWORD.getMessage());

                return baseResultVo;
            }
        }

        //拼装accessToken
        String accessToken = JwtUtil.createJWT(loginParameter, user, audience);

        //返回accessToken
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setAccessToken(accessToken);
        accessTokenEntity.setExpiresIn(audience.getExpiresSecond());
        accessTokenEntity.setTokenType("bearer");

        baseResultVo.setCode(ResultStatusCode.OK.getCode());
        baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        baseResultVo.setData(accessTokenEntity);
        return baseResultVo;
    }
}