package org.example.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.dao.BookDaoImpl;
import org.example.dao.BookInfoDao;
import org.example.pojo.BookInfo;

public class BookServiceImpl implements BookService {
    private SqlSessionFactory sqlSessionFactory;
// 测试类会调用BookServiceImpl方法，传入一个SqlSessionFactoryBuilder根据流来创建的sqlSessionFactory对象
    public BookServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public BookInfo getBookBybookName(String bookName) {
        BookInfoDao bookInfoDao=new BookDaoImpl(this.sqlSessionFactory);
        BookInfo book =bookInfoDao.getBookBybookName(bookName);
        return book;
    }
   public void insertBook(BookInfo book){
       BookInfoDao bookInfoDao=new BookDaoImpl(this.sqlSessionFactory);
       bookInfoDao.insertBook(book);
   }
   public void updateBook(BookInfo book){
       BookInfoDao bookInfoDao=new BookDaoImpl(this.sqlSessionFactory);
       bookInfoDao.updateBook(book);
   }
   public int deleteBook(Integer id){
       BookInfoDao bookInfoDao=new BookDaoImpl(this.sqlSessionFactory);
       int rs=bookInfoDao.deleteBook(id);
       return rs;
   }
}
