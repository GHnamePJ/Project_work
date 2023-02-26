package org.example.service;

import org.example.pojo.BookInfo;

import java.util.List;

public interface BookService {
    List<BookInfo> getBookByName(String bookName);
    BookInfo getBookById(Integer id);
    int insertBook(BookInfo book);
    int updateBook(BookInfo book);
    int deleteBook(Integer id);
}
