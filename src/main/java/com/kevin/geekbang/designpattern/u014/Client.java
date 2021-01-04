package com.kevin.geekbang.designpattern.u014;

import com.kevin.geekbang.designpattern.u014.auth.DefaultAuthenticatorImpl;
import com.kevin.geekbang.designpattern.u014.auth.IApiAuthenticator;
import com.kevin.geekbang.designpattern.u014.storage.DefaultCredentialStorageImpl;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 14:22 2020/12/10
 * @ProjectName: design-patterns
 */
public class Client {

    private static final String SALT = "u014";

    public static void main(String[] args) throws Exception {
        StringBuilder reqUrl = new StringBuilder();
        long timestamp = System.currentTimeMillis();
        reqUrl.append("http://geekbang?")
                .append("appId=u0001&")
                .append("timestamp=").append(timestamp).append("&")
                .append("token=").append(createClientToken(timestamp));

        IApiAuthenticator authenticator = new DefaultAuthenticatorImpl(new DefaultCredentialStorageImpl());
        authenticator.auth(reqUrl.toString());
        System.out.println("success");
        Thread.sleep(60 * 1000);
        authenticator.auth(reqUrl.toString());
    }

    private static String createClientToken(long timestamp){
        StringBuilder reqUrl = new StringBuilder();
        reqUrl.append("http://geekbang?")
                .append("appId=u0001&")
                .append("password=123456&")
                .append("timestamp=").append(timestamp);
        String token = DigestUtils.md5Hex(DigestUtils.md5Hex(reqUrl.toString()) + SALT);
        System.out.println("client token:" + token);
        return token;
    }

}
