package com.kevin.geekbang.designpattern.u014.auth;

import com.kevin.geekbang.designpattern.u014.apirequest.ApiRequest;
import com.kevin.geekbang.designpattern.u014.storage.ICredentialStorage;
import com.kevin.geekbang.designpattern.u014.token.AuthToken;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 13:48 2020/12/10
 * @ProjectName: design-patterns
 */

public class DefaultAuthenticatorImpl implements IApiAuthenticator{

    // 注入
    private ICredentialStorage iCredentialStorage;

    public DefaultAuthenticatorImpl(ICredentialStorage iCredentialStorage) {
        this.iCredentialStorage = iCredentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest req = ApiRequest.createFullUrl(url);
        auth(req);
    }

    @Override
    public void auth(ApiRequest req) {
        AuthToken clientToken = new AuthToken(req.getToken(), req.getTimestamp());
        if (clientToken.isExpired()){
            throw new RuntimeException("Token is expired.");
        }

        String password = iCredentialStorage.getPasswordByAppID(req.getAppId());
        Map<String, String> params = new TreeMap<>();
        params.put("appId", req.getAppId());
        params.put("password", password);

        AuthToken serverToken = AuthToken.createToken(req.getBaseUrl(), req.getTimestamp(), params);
        if (!serverToken.match(clientToken)){
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
