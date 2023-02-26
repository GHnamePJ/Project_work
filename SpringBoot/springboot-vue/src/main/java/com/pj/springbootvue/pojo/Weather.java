package com.pj.springbootvue.pojo;

import java.sql.Date;
/**
 * @author : Pj-Xk
 * @date : 2022-10-15 23:18
 **/
public class Weather {
    private Date date;
    private String temperature;
    private String PM25;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPM25() {
        return PM25;
    }

    public void setPM25(String PM25) {
        this.PM25 = PM25;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date=" + date +
                ", temperature='" + temperature + '\'' +
                ", PM25='" + PM25 + '\'' +
                '}';
    }
}
