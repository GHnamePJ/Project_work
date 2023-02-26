package cn.mybatis.dynamic.relation.po;

public class EmployeeInfo {
	private Integer id;
	private String empName;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}	
	@Override
	public String toString(){
		return "EmployeeInfo:id ="+ id + " empName=" + empName;
	}
	
}
