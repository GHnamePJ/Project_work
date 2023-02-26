package cn.mybatis.dynamic.relation.po;

import java.util.List;

// Course m  <---->  Student n
public class Course {
	private Integer id;
	private String cname;
	private Float credit;
	private List<StudentInfo> students;

	public Integer getId() {
		return id;
	}

	public String getCname() {
		return cname;
	}

	public Float getCredit() {
		return credit;
	}

	public List<StudentInfo> getStudents() {
		return students;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setCredit(Float credit) {
		this.credit = credit;
	}

	public void setStudents(List<StudentInfo> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", cname='" + cname + '\'' +
				", credit=" + credit +
				", students=" + students +
				'}';
	}
}
