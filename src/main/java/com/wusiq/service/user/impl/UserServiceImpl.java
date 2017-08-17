package com.wusiq.service.user.impl;

import com.wusiq.entity.UserEntity;
import com.wusiq.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * user服务接口实现类
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private RedisTemplate<String,UserEntity> redisTemplate;

    @Override
    public void addUser(String key,UserEntity user) {
        redisTemplate.opsForSet().add(key,user);
    }

    @Override
    public Set<UserEntity> queryByKey(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
