package cn.mybatis.example.dao.daoImpl;

import cn.mybatis.example.dao.po.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserInfo getUserById(int id) {
        SqlSession session = this.sqlSessionFactory.openSession();
        UserInfo user = session.selectOne("getUserById", id);
        session.close();
        return user;
    }

    @Override
    public List<UserInfo> getUserByName(String realName) {
        SqlSession session = this.sqlSessionFactory.openSession();
        List<UserInfo> userList = session.selectList("getUserByName", realName);
        session.close();
        return userList;
    }

    @Override
    public void insertUser(UserInfo user) {
        SqlSession session = this.sqlSessionFactory.openSession();
        session.insert("insertUser", user);

        session.commit();
        session.close();
    }

    @Override
    public void updateUser(UserInfo user) {
        SqlSession session = this.sqlSessionFactory.openSession();
        session.update("updateUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(int id) {
        SqlSession session = this.sqlSessionFactory.openSession();
        session.delete("deleteUser", id);
        session.commit();
        session.close();
    }
}
