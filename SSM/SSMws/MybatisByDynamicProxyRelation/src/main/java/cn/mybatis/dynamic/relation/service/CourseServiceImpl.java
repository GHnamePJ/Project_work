package cn.mybatis.dynamic.relation.service;

import java.util.List;

import cn.mybatis.dynamic.relation.mapper.CourseMapper;
import cn.mybatis.dynamic.relation.po.Course;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class CourseServiceImpl implements CourseService{

	private SqlSessionFactory sqlSessionFactory;
	
	public CourseServiceImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Course> getCourseInfo() {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		CourseMapper courseMapper = session.getMapper(CourseMapper.class);
		List<Course> clist = courseMapper.getCourseInfo();
		session.close();
		return clist;
		
	}

}
