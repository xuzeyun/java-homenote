package com.example.homenote.exception;

public enum BusinessExceptionCode {
    /* 定义了各种异常的 key value 信息 */
    USER_USERNAME_EXIST("用户名已存在"),
    LOGIN_USER_EXIST("用户名不存在或密码错误"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) { this.desc = desc; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }
}
