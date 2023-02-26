package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.BookInfoMapper;
import org.example.pojo.BookInfo;

import java.util.List;

public class BookServiceImpl implements BookService{
    private SqlSessionFactory sqlSessionFactory;
    public BookServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
        @Override
        public List<BookInfo> getBookByName(String bookName){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        List<BookInfo> book=bookInfoMapper.getBookByName(bookName);
        sqlSession.commit();
        sqlSession.close();
        return book;
    }
        @Override
        public BookInfo getBookById(Integer id){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        BookInfo book=bookInfoMapper.getBookById(id);
        sqlSession.commit();
        sqlSession.close();
        return book;
    }
        @Override
        public int insertBook(BookInfo book){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.insertBook(book);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
        @Override
        public int updateBook(BookInfo book){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.updateBook(book);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
        @Override
        public int deleteBook(Integer id){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.deleteBook(id);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
}
