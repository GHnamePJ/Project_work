package org.example.dao;

import org.example.pojo.BookInfo;

public interface BookInfoDao {
    BookInfo getBookBybookName(String bookName);
    void insertBook(BookInfo book);
    void updateBook(BookInfo book);
    int deleteBook(Integer id);
}
