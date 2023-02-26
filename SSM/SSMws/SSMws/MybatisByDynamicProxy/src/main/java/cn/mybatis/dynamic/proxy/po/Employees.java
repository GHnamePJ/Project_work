package cn.mybatis.dynamic.proxy.po;

public class Employees {
    private int id;
    private String empName;

    public int getId() {
        return id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                '}';
    }
}
