package org.example.dao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.pojo.BookInfo;

public class BookDaoImpl implements  BookInfoDao{
    private SqlSessionFactory sqlsessionFactory;
    public BookDaoImpl(SqlSessionFactory sqlsessionFactory){
        this.sqlsessionFactory=sqlsessionFactory;
    }

    @Override
    public BookInfo getBookBybookName(String bookName) {
        SqlSession sqlSession=this.sqlsessionFactory.openSession();
        BookInfo book=sqlSession.selectOne("forCurd.getBookBybookName",bookName);
        sqlSession.close();
        return  book;
    }
    @Override
   public void insertBook(BookInfo book){
        SqlSession sqlSession=this.sqlsessionFactory.openSession();
        sqlSession.insert("insertBook",book);
        sqlSession.commit();
        sqlSession.close();

    }
    @Override
   public void updateBook(BookInfo book){
        SqlSession sqlSession=this.sqlsessionFactory.openSession();
        sqlSession.update("updateBook",book);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
   public int deleteBook(Integer id){
        SqlSession sqlSession=this.sqlsessionFactory.openSession();
        int rs=sqlSession.delete("deleteBook",id);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
}
