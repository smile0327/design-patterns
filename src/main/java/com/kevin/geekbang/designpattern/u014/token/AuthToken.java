package com.kevin.geekbang.designpattern.u014.token;

import com.sun.crypto.provider.HmacMD5;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

/**
 * @Auther: kevin
 * @Description:  token 验证相关
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 9:19 2020/12/10
 * @ProjectName: design-patterns
 */
public class AuthToken {

    private static final String SALT = "u014";

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 30 * 1000;

    private String token;

    private long createTime;

    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this(token, createTime);
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public String getToken(){
        return this.token;
    }

    /**
     *  是否过期
     * @return
     */
    public boolean isExpired(){
        return System.currentTimeMillis() - createTime > expiredTimeInterval;
    }

    /**
     * 匹配token
     * @param authToken
     * @return
     */
    public boolean match(AuthToken authToken){
        return this.token.equals(authToken.token);
    }

    /**
     * 创建token
     *  将 url + appId + password + timestamp
     * @param baseUrl
     * @param createTime
     * @param params
     * @return
     */
    public static AuthToken createToken(String baseUrl, long createTime, Map<String, String> params){
        StringBuilder url = new StringBuilder();
        url.append(baseUrl)
                .append("?");
        params.forEach((k,v)->{
            url.append(k).append("=").append(v).append("&");
        });
        url.append("timestamp")
                .append("=")
                .append(createTime);
        // 加盐后再次加密
        String token = DigestUtils.md5Hex(DigestUtils.md5Hex(url.toString()) + SALT);
        System.out.println("server token:" + token);
        return new AuthToken(token, createTime);
    }

}
