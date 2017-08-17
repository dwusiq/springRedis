package com.wusiq.test;

import com.wusiq.config.ApplicationConfig;
import com.wusiq.config.RootConfig;
import com.wusiq.service.stringRedisTemplate.StringRedisTemplateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * stringRedisTemplate测试类
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebAppInitializer.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = ApplicationConfig.class)})
public class StringRedisTemplateTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    StringRedisTemplateService stringRedisTemplateService;

    /**
     * 测试添加String方法
     */
    @Test
    public void testAddString() throws Exception {
        Assert.assertNotNull(wac);
        stringRedisTemplateService.addString("name","wusiq");
    }

    /**
     * 测试查询String方法
     */
    @Test
    public void testQueryString() throws Exception {
        Assert.assertNotNull(wac);
        String nameValue = stringRedisTemplateService.queryString("name");
        Assert.assertEquals("wusiq",nameValue);
    }

    /**
     * 测试添加list方法
     */
    @Test
    public void testAddList() throws Exception {
        Assert.assertNotNull(wac);
        stringRedisTemplateService.addList("ListKey","wicker");
    }

    /**
     * 测试查询list方法
     */
    @Test
    public void testQueryList() throws Exception {
        Assert.assertNotNull(wac);
        List<String> list = stringRedisTemplateService.queryList("ListKey", 0L,-1L);
        Assert.assertNotNull(list);
        System.out.println(list.toString());
    }

    /**
     * 测试添加Set方法
     */
    @Test
    public void testAddSet() throws Exception {
        Assert.assertNotNull(wac);
        stringRedisTemplateService.addSet("SetKey","wicker");
        stringRedisTemplateService.addSet("SetKey","wusiq");
        stringRedisTemplateService.addSet("SetKey","wusiq");
    }

    /**
     * 测试查询Set方法
     */
    @Test
    public void testQuerySet() throws Exception {
        Assert.assertNotNull(wac);
        Set<String> set = stringRedisTemplateService.querySet("SetKey");
        Assert.assertNotNull(set);
        System.out.println(set.toString());
    }


    /**
     * 测试添加Zet方法
     */
    @Test
    public void testAddZet() throws Exception {
        Assert.assertNotNull(wac);
        stringRedisTemplateService.addZet("ZSetKey","wicker",3);
        stringRedisTemplateService.addZet("ZSetKey","wusiq",1);
        stringRedisTemplateService.addZet("ZSetKey","lalala",2);
    }

    /**
     * 测试查询ZSet方法
     */
    @Test
    public void testQueryZSet() throws Exception {
        Assert.assertNotNull(wac);
        Set<String> set = stringRedisTemplateService.queryZet("ZSetKey",0L,-1L);
        Assert.assertNotNull(set);
        System.out.println(set.toString());
    }

    /**
     * 测试添加Hash方法
     */
    @Test
    public void testAddHash() throws Exception {
        Assert.assertNotNull(wac);
        stringRedisTemplateService.addHash("hashKey","wicker","girl");
        stringRedisTemplateService.addHash("hashKey","wusiq","boy");
    }

    /**
     * 测试查询ZSet方法
     */
    @Test
    public void testQueryHash() throws Exception {
        Assert.assertNotNull(wac);
        Map<Object,Object> map = stringRedisTemplateService.queryHash("hashKey");
        Assert.assertNotNull(map);

        Set<Map.Entry<Object, Object>> set = map.entrySet();
        for(Map.Entry<Object, Object> entity:set){
            System.out.println(entity.getKey()+":"+entity.getValue());
        }

    }
}
