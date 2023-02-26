package com.pj.springbootvue.http;

import com.pj.springbootvue.pojo.User;

import java.io.Serializable;

/**
 * @author : Pj-Xk
 * @date : 2022-10-22 10:43
 **/
public class ReturnCode<T> implements Serializable {
    private  int success;
    private  String msg;
    private Object data;
    public ReturnCode(int success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    public ReturnCode(int success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
