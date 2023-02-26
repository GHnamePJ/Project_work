package org.example;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.example.pojo.BookInfo;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
     @Test
    public void testgetBookBybookName(){
         String bookName="y";
         BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
         BookInfo book=bookService.getBookBybookName(bookName);
         System.out.println(book);
     }
    @Test
    public void testinsertBook(){
        BookInfo book=new BookInfo();
        book.setId(1);
        book.setBookName("c语言");
        book.setPrice("2");
        book.setPublisher("科技");
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        bookService.insertBook(book);
        System.out.println("插入一条信息成功！");
    }
    @Test
    public void testupdateBook(){
        BookInfo book=new BookInfo();
        book.setId(1);
        book.setBookName("c语言");
        book.setPrice("30");
        book.setPublisher("科技");
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        bookService.updateBook(book);
        System.out.println("修改一条数据成功！");
    }
    @Test
    public void testdeleteBook(){
        Integer id =1;
        BookService bookService=new BookServiceImpl(this.sqlSessionFactory);
        int rs=bookService.deleteBook(id);
        System.out.println("删除"+rs+"条数据成功！");
    }
}
