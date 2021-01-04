package com.kevin.geekbang.designpattern.u014.apirequest;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: kevin
 * @Description:  url处理相关
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 11:40 2020/12/10
 * @ProjectName: design-patterns
 */
public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;


    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ApiRequest createFullUrl(String fullUrl){
        Map<String, String> parse = parse(fullUrl);
        return new ApiRequest(parse.get("baseUrl"), parse.get("token"), parse.get("appId"), Long.valueOf(parse.get("timestamp")));
    }

    private static Map<String, String> parse(String fullUrl){
        Map<String, String> parseParam = new TreeMap<>();
        String[] split = fullUrl.split("\\?");
        String baseUrl = split[0].trim();
        parseParam.put("baseUrl",baseUrl);
        String[] params = split[1].split("&");
        Arrays.stream(params).forEach(l -> {
            String[] param = l.split("=");
            parseParam.put(param[0], param[1]);
        });
        return parseParam;
    }

}
