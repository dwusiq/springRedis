package com.wusiq.service.string;

/**
 * 非实体的服务，如<String,String>
 */
public interface StringService {
    /*添加String*/
    void addString(String key,String value);
    /*查询String*/
    String queryString(String key);
}
