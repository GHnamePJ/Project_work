package cn.mybatis.dynamic.relation.po;
/*
 * Orders n <---->  EmployeeInfo 1 
 */
public class Orders {
	private Integer id;
	private String orderDate;
	private EmployeeInfo emp_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public EmployeeInfo getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(EmployeeInfo emp_id) {
		this.emp_id = emp_id;
	}
	@Override
	public String toString(){
		return "Orders:id ="+ id + " orderDate=" + orderDate + 
				" emp_id=" + emp_id;
	}	
}
