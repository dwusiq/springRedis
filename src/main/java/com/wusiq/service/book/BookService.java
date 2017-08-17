package com.wusiq.service.book;

import com.wusiq.entity.BookEntity;

import java.util.Set;

/**
 * book服务接口
 */
public interface BookService {
    void addBook(String key,BookEntity user);
    Set<BookEntity> queryByKey(String key);
}
