package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.BookInfo;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public class AppTest
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
    @Test
    public void testgetBookByName(){
        String bookName="设";
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        List<BookInfo> book=bookService.getBookByName(bookName);
        for(BookInfo bookInfo:book){
            System.out.println(bookInfo);
        }
    }
    @Test
    public void testgetBookById(){
        Integer id=2;
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        BookInfo book=bookService.getBookById(id);
        System.out.println(book);
    }
    @Test
    public void testinsertBook(){
        BookInfo book=new BookInfo();
        book.setId(4);
        book.setBookName("HTML");
        book.setPrice(BigDecimal.valueOf(60));
        book.setPublisher("教育");
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        int rs= bookService.insertBook(book);
        System.out.println("插入"+rs+"条数据成功！！！"+book);
    }
    @Test
    public void testupdateBook(){
        BookInfo book=new BookInfo();
        book.setId(3);
        book.setBookName("Android");
        book.setPrice(BigDecimal.valueOf(50));
        book.setPublisher("教育");
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        int rs=bookService.updateBook(book);
        System.out.println("修改"+rs+"条数据成功！！！"+book);
    }
    @Test
    public void testdeleteBook(){
        Integer id=2;
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        int rs=bookService.deleteBook(id);
        System.out.println("删除"+rs+"条数据成功！！！");
    }
}
