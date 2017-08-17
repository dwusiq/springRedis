package com.wusiq.service.user;

import com.wusiq.entity.UserEntity;

import java.util.Set;

/**
 * user服务接口(使用基于UserEntity实体的Redis模板bean)
 */
public interface UserService {
    void addUser(String key,UserEntity user);
    Set<UserEntity> queryByKey(String key);
}
