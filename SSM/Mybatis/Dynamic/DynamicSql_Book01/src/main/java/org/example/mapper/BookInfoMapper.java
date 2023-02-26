package org.example.mapper;

import org.example.pojo.BookInfo;

public interface BookInfoMapper {
    BookInfo getBookById(int id);
    int insertBook(BookInfo book);
    int updateBook(BookInfo book);
    int deleteBook(int id);
}
