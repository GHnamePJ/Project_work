package cn.mybatis.dynamic.relation.service;

import java.util.List;

import cn.mybatis.dynamic.relation.mapper.StudentInfoMapper;
import cn.mybatis.dynamic.relation.po.StudentInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class StudentInfoServiceImpl implements StudentInfoService {

	private SqlSessionFactory sqlSessionFactory;
	
	public StudentInfoServiceImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public List<StudentInfo> getStudentInfo() {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		StudentInfoMapper studentCourseMapper = session.getMapper(StudentInfoMapper.class);
		List<StudentInfo> slist = studentCourseMapper.getStudentInfo();
		session.close();
		return slist;
	}

}
