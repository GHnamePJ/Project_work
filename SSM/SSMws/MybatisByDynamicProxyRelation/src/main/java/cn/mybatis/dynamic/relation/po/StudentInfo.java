package cn.mybatis.dynamic.relation.po;

import java.util.List;

public class StudentInfo {
	private Integer id;
	private String sno;
	private String sname;
	private List<Course> courses;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString(){
		String ids = "";
		if(courses != null) {
			for(int i=0; i<courses.size(); i++) {
				ids = ids + courses.get(0).getId() + ";"; 
			}
		}		
		return "StudentInfo:id ="+ id + " sno=" + sno + " sname=" + sname + " course=" + ids;
	}
	
}
