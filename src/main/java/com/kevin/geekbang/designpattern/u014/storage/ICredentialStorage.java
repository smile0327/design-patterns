package com.kevin.geekbang.designpattern.u014.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 11:56 2020/12/10
 * @ProjectName: design-patterns
 */
public interface ICredentialStorage {

    static Map<String, String> account = new HashMap<String, String>(){
        {
            put("u0001","123456");
            put("u0002", "000000");
        }
    };

    String getPasswordByAppID(String appId);

}
