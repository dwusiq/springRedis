package com.wusiq.service.stringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 非实体的服务，如<String,String>
 */
public interface StringRedisTemplateService {
    /*添加String*/
    void addString(String key,String value);
    /*查询String*/
    String queryString(String key);
    /*添加list*/
    void addList(String key,String value);
    /*查询list*/
    List<String> queryList(String key,Long start,Long end);
    /*添加set*/
    void addSet(String key, String value);
    /*获取set*/
    Set<String> querySet(String key);
    /*添加set*/
    void addZet(String key, String value,double score);
    /*获取Zset*/
    Set<String> queryZet(String key, long start, long end);
    /*添加Hash*/
    void addHash(String name,String key, Object value);
    /*获取Hash*/
    Map<Object,Object> queryHash(String name);
}
