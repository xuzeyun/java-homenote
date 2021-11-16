package com.example.homenote.res;

public class CommonRes<T> {
//    业务上的成功或失败
    private Boolean success = true;

//    返回信息
    private String msg;

//    返回泛型数据，自定义类型
    private T data;

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Boolean getSuccess() {
        return success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
}
