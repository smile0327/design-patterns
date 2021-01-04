package com.kevin.geekbang.designpattern.u014.storage;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 11:56 2020/12/10
 * @ProjectName: design-patterns
 */
public class DefaultCredentialStorageImpl implements ICredentialStorage {

    @Override
    public String getPasswordByAppID(String appId) {
        return account.get(appId);
    }

}
