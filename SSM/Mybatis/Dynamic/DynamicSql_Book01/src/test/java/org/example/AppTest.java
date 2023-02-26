package org.example;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.BookInfo;
import org.example.service.BookInfoService;
import org.example.service.BookInfoServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


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
    public void testgetBookById(){
        int id=1;
        BookInfoService bookInfoService=new BookInfoServiceImpl(this.sqlSessionFactory);
        BookInfo book=bookInfoService.getBookById(id);
        System.out.println("查找到："+book);
    }
    @Test
    public void testgetinsertBook(){
        BookInfo book=new BookInfo();
        book.setId(1);
        book.setBookName("网络基础技术");
        book.setPrice(BigDecimal.valueOf(30));
        book.setPublisher("世纪出版社");
        BookInfoService bookInfoService=new BookInfoServiceImpl(this.sqlSessionFactory);
        int rs=bookInfoService.insertBook(book);
        System.out.println("插入"+rs+"条信息成功！");
    }
    @Test
    public void testupdateBook(){
        BookInfo book=new BookInfo();
        book.setBookName("23种常见设计模式");
        BookInfoService bookInfoService=new BookInfoServiceImpl(this.sqlSessionFactory);
        int rs=bookInfoService.updateBook(book);
        System.out.println("修改"+rs+"条信息成功！");

    }
    @Test
    public void testdelateBook(){
        int id=1;
        BookInfoService bookInfoService=new BookInfoServiceImpl(this.sqlSessionFactory);
        int rs=bookInfoService.deleteBook(id);
        System.out.println("删除"+rs+"条信息成功！");
    }
}
