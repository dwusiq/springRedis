package com.wusiq.test;

import com.alibaba.fastjson.JSON;
import com.wusiq.config.ApplicationConfig;
import com.wusiq.config.RootConfig;
import com.wusiq.entity.UserEntity;
import com.wusiq.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

/**
     * 用户管理测试类
     */

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration(value = "src/main/webapp")
    @ContextHierarchy({
            @ContextConfiguration(name = "parent", classes = RootConfig.class),
            @ContextConfiguration(name = "child", classes = ApplicationConfig.class)})
    public class UserTest {
        @Autowired
        private WebApplicationContext wac;

        @Autowired
        UserService userService;

        /**
         * 测试添加UserEntity方法
         */
        @Test
        public void testAddUser() throws Exception {
            Assert.assertNotNull(wac);
            UserEntity user1 = new UserEntity();
            user1.setId(1L);
            user1.setUserName("wusiq");
            user1.setAge(12);
/*
            UserEntity user2 = new UserEntity();
            user2.setId(2L);
            user3.setUserName("wicker");
            user3.setAge(13);
            userService.addUser("us",user3);*/
            userService.addUser("us",user1);
        }

        /**
         * 测试查询UserEntity方法
         */
        @Test
        public void testQueryUser() throws Exception {
            Assert.assertNotNull(wac);
            Set<UserEntity> set = userService.queryByKey("us");
            for(UserEntity user:set){
                System.out.print(JSON.toJSONString(user));
            }
        }
}
