package com.wusiq.test;

import com.wusiq.config.ApplicationConfig;
import com.wusiq.config.RootConfig;
import com.wusiq.config.WebAppInitializer;
import com.wusiq.service.string.StringService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户管理测试类
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebAppInitializer.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = ApplicationConfig.class)})
public class UserTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    StringService stringService;

    /**
     * 测试添加方法
     */
    @Test
    public void testAddString() throws Exception {
        Assert.assertNotNull(wac);
        stringService.addString("name","wusiq");
    }

    /**
     * 测试查询方法
     */
    @Test
    public void testQueryString() throws Exception {
        Assert.assertNotNull(wac);
        String nameValue = stringService.queryString("name");
        Assert.assertEquals("wusiq",nameValue);
    }
}
