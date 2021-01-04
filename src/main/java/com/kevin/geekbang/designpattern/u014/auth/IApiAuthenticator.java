package com.kevin.geekbang.designpattern.u014.auth;

import com.kevin.geekbang.designpattern.u014.apirequest.ApiRequest;

/**
 * @Auther: kevin
 * @Description:  外部调用执行入口
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 13:46 2020/12/10
 * @ProjectName: data-sync
 */
public interface IApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);

}
