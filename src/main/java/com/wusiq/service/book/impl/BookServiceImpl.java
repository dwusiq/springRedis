package com.wusiq.service.book.impl;

import com.wusiq.entity.BookEntity;
import com.wusiq.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Set;

/**
 * book服务接口实现类
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private RedisTemplate<String,BookEntity> redisTemplate;

    @Override
    public void addBook(String key, BookEntity book) {
        redisTemplate.opsForSet().add(key,book);
    }

    @Override
    public Set<BookEntity> queryByKey(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
