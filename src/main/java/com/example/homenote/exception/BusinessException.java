package com.example.homenote.exception;

public class BusinessException extends RuntimeException {
//    定义枚举
    private BusinessExceptionCode code;

//    当构造这个自定义异常时，需要把 code 传入
    public BusinessException(BusinessExceptionCode code){
        super(code.getDesc());
        this.code = code;
    }

//    set get 方法
    public BusinessExceptionCode getCode() { return code; }

    public void setCode(BusinessExceptionCode code) { this.code = code; }

    /* 不写入堆栈信息 提高性能 */
    @Override
    public Throwable fillInStackTrace() { return this; }

}
