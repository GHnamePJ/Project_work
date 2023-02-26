package org.example.pojo;

import java.util.Date;

public class order {
    private String Onum;
    private String Unum;
    private String date;

    public String getOnum() {
        return Onum;
    }

    public void setOnum(String onum) {
        Onum = onum;
    }

    public String getUnum() {
        return Unum;
    }

    public void setUnum(String unum) {
        Unum = unum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "order{" +
                "Onum='" + Onum + '\'' +
                ", Unum='" + Unum + '\'' +
                ", date=" + date +
                '}';
    }
}
