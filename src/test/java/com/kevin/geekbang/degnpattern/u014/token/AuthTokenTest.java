package com.kevin.geekbang.degnpattern.u014.token;

import com.kevin.geekbang.designpattern.u014.token.AuthToken;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 11:20 2020/12/10
 * @ProjectName: design-patterns
 */
public class AuthTokenTest {

    @Test
    public void createTokenTest(){
        String baseUrl = "https://time.geekbang";
        long createTime = System.currentTimeMillis();
        Map<String, String> param = new TreeMap<>();
        param.put("AppId", "u0001");
        param.put("password", "123456");
        AuthToken token = AuthToken.createToken(baseUrl, createTime, param);
        System.out.println(token.getToken());
        System.out.println(System.currentTimeMillis());
    }

}
