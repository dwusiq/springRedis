package com.wusiq.service.stringRedisTemplate.impl;

import com.wusiq.service.stringRedisTemplate.StringRedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 非实体的服务接口实现类，如<String,String>
 */
@Service
public class StringRedisTemplateServiceImpl implements StringRedisTemplateService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addString(String key, String value) {
        this.stringRedisTemplate.opsForValue().set(key, value,60*10, TimeUnit.SECONDS);
    }

    @Override
    public String queryString(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void addList(String key, String value) {
        this.stringRedisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public List<String> queryList(String key,Long start,Long end) {
        return this.stringRedisTemplate.opsForList().range(key,start,end);
    }

    @Override
    public void addSet(String key, String value) {
        this.stringRedisTemplate.opsForSet().add(key,value);
    }

    @Override
    public Set<String> querySet(String key) {
        return this.stringRedisTemplate.opsForSet().members(key);
    }

    @Override
    public void addZet(String key, String value,double score) {
        this.stringRedisTemplate.opsForZSet().add(key,value,score);
    }

    @Override
    public Set<String> queryZet(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().range(key,start,end);
    }

    @Override
    public void addHash(String name, String key, Object value) {
        this.stringRedisTemplate.opsForHash().put(name,key,value);
    }

    @Override
    public Map<Object,Object> queryHash(String name) {
        return this.stringRedisTemplate.opsForHash().entries(name);
    }
}
