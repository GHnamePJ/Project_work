package org.example.service;

import org.example.pojo.BookInfo;

public interface BookInfoService {
    BookInfo getBookById(int id);
    int insertBook(BookInfo book);
    int updateBook(BookInfo book);
    int deleteBook(int id);
}
