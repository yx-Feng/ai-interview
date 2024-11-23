package org.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 风间影月
 * @description 签名工具
 */
@Slf4j
public class GLMTokenUtils {

    private static final String apiKey = "123";
    private static final String apiSecret = "123";

    // 过期时间；默认30分钟
    private static final int expireMillis = 30 * 60 * 1000;

    /**
     * 对 API KeyS 进行签名
     * @param apiKey        https://open.bigmodel.cn/usercenter/apikeys
     * @param apiSecret     apiKey的后半部分 9b4e111a90b111111154a43222191.diWL222222ne22Bp 的 diWL222222ne22Bp
     * @return
     */
    public static String generateToken(String apiKey, String apiSecret) {
        // 创建Token
        Algorithm algorithm = Algorithm.HMAC256(apiSecret.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> payload = new HashMap<>();
        payload.put("api_key", apiKey);
        payload.put("exp", System.currentTimeMillis() + expireMillis);
        payload.put("timestamp", System.currentTimeMillis());
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("sign_type", "SIGN");
        String token = JWT.create().withPayload(payload).withHeader(headerClaims).sign(algorithm);
        return token;
    }

    public static String generateToken() {
        return generateToken(apiKey, apiSecret);
    }

    public static void main(String[] args) {
        System.out.println(generateToken());
    }

}

