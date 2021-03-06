package com.xxm.mmd.common.base;

/**
 * Created by MaDeng on 2018/5/26.
 */
public class BaseBean<T> {


    public int code;
    public String msg;
    public T data;

    public boolean state() {
        //1 ok
        return code != 1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
