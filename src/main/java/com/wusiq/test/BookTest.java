package com.wusiq.test;

import com.alibaba.fastjson.JSON;
import com.wusiq.config.ApplicationConfig;
import com.wusiq.config.RootConfig;
import com.wusiq.entity.BookEntity;
import com.wusiq.entity.UserEntity;
import com.wusiq.service.book.BookService;
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
 * 书本管理测试类(使用基于UserEntity实体的Redis模板bean)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = ApplicationConfig.class)})
public class BookTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    BookService bookService;

    /**
     * 测试添加BookEntity方法
     */
    @Test
    public void testAddBook() throws Exception {
        Assert.assertNotNull(wac);
        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setBookName("大浪淘沙");
        book1.setBookPage(3);

        BookEntity book2 = new BookEntity();
        book2.setId(2L);
        book2.setBookName("淘沙小能手");
        book2.setBookPage(4);
        bookService.addBook("allBooks",book1);
        bookService.addBook("allBooks",book2);
    }

    /**
     * 测试查询BookEntity方法
     */
    @Test
    public void testQueryBook() throws Exception {
        Assert.assertNotNull(wac);
        Set<BookEntity> set = bookService.queryByKey("allBooks");
        for(BookEntity book:set){
            System.out.print(JSON.toJSONString(book));
        }
    }
}
