package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.BookInfoMapper;
import org.example.pojo.BookInfo;

public class BookInfoServiceImpl implements BookInfoService{
    private SqlSessionFactory sqlSessionFactory;
    public BookInfoServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public BookInfo getBookById(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        BookInfo book=bookInfoMapper.getBookById(id);
//        可以不用提交事物
        sqlSession.close();
        return book;
    }
    @Override
    public int insertBook(BookInfo book){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.insertBook(book);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
    @Override
    public int updateBook(BookInfo book){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.updateBook(book);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
    @Override
    public int deleteBook(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BookInfoMapper bookInfoMapper=sqlSession.getMapper(BookInfoMapper.class);
        int rs=bookInfoMapper.deleteBook(id);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
}
