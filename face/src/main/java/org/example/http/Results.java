package org.example.http;

import java.io.Serializable;

public class Results<T> implements Serializable {
    private boolean success;//标识是否成功操作
    private String message;//需要传递的信息
    private T date;//需要传递的数据
    public Results(boolean success,String message){
        super();
        this.success=success;
        this.message=message;
    }
    public Results(boolean success,String message,T date){
        super();
        this.success=success;
        this.message=message;
        this.date=date;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
