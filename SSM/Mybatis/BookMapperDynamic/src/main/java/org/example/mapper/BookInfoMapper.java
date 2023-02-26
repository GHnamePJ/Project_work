package org.example.mapper;

import org.example.pojo.BookInfo;

import java.util.List;

public interface BookInfoMapper {
        List<BookInfo> getBookByName(String bookName);
        BookInfo getBookById(Integer id);
        int insertBook(BookInfo book);
        int updateBook(BookInfo book);
        int deleteBook(Integer id);
}
