package cn.mybatis.dynamic.relation.po;
/*
 * LoginAccount 1 <--->  EmployeeInfo 1
 */
public class LoginAccount {
	Integer id;
	String loginName;
	String password;
	EmployeeInfo emp_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmployeeInfo getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(EmployeeInfo emp_id) {
		this.emp_id = emp_id;
	}
	@Override
	public String toString(){
		return "login account: id=" + id + "loginName=" + loginName + 
				"password=" + password + "emp_id=" + emp_id;
	}	
}
