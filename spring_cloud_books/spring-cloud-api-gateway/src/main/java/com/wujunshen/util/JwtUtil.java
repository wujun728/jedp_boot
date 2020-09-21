package com.wujunshen.util;

import com.wujunshen.entity.User;
import com.wujunshen.security.Audience;
import com.wujunshen.security.LoginParameter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:16:44 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception ex) {
            LOGGER.error("exception message is: " + ex.getMessage());
            return null;
        }
    }

    public static String createJWT(LoginParameter loginParameter, User user, Audience audience) {
        long ttlmillis = audience.getExpiresSecond() * 1000;
        String base64Security = audience.getBase64Secret();
        Date now = new Date(System.currentTimeMillis());

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", user.getRole())
                .claim("unique_name", loginParameter.getUserName())
                .claim("userid", user.getName())
                .setIssuer(audience.getName())
                .setAudience(audience.getClientId())
                .signWith(SignatureAlgorithm.HS256, signingKey);

        //添加Token过期时间
        if (ttlmillis >= 0) {
            Date exp = new Date(System.currentTimeMillis() + ttlmillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}