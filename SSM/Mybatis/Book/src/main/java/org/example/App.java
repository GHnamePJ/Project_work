package org.example;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.example.dao.BookDaoImpl;
import org.example.pojo.BookInfo;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;

import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
