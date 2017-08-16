package com.wusiq.service.string.impl;

import com.wusiq.service.string.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 非实体的服务接口实现类，如<String,String>
 */
@Service
public class StringServiceImpl implements StringService {

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
}
