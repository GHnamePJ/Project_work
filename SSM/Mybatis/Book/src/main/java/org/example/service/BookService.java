package org.example.service;
import org.example.pojo.BookInfo;

public interface BookService {
    BookInfo getBookBybookName(String bookName);
    void insertBook(BookInfo book);
    void updateBook(BookInfo book);
    int deleteBook(Integer id);
}
