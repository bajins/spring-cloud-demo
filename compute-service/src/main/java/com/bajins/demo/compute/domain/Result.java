package com.bajins.demo.compute.domain;

/**
 * @Description:
 * @Author: https://www.bajins.com
 * @File: Result.java
 * @Version: 1.0.0
 * @Time: 2020/3/23/023 17:14
 * @Project: spring-cloud-demo
 * @Package: com.bajins.demo.domain
 * @Software: IntelliJ IDEA
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认成功
     *
     * @param data
     */
    public Result(T data) {
        this(200, "操作成功", data);
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

    // ------------------ 链式编程，返回类本身 ------------------

    public Result code(int code) {
        this.setCode(code);
        return this;
    }

    public Result msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }

}
