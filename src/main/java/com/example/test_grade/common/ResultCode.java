package com.example.test_grade.common;

public enum ResultCode {
    //枚举值（作为状态码）
    SUCCESS(200, "成功"),
    FAIL(-1, "失败"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    //自定义的状态码(码值是自拟)
    ID_NOT_FOUND(4001,"id不存在"),
    NAME_DUPLICATED(4002,"输入内容已存在");
    //枚举类两个成员
    private int code;
    private String message;
    // 枚举类也有构造函数、getter方法（没有setter）
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
