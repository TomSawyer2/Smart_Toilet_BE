package com.st.common.api;

public enum ResultCode implements ErrorCode {
    FAILED(-1, "操作失败"),
    SUCCESS(0, "操作成功"),
    USERNAME_EXIST(1, "用户名已存在"),
    USER_NOT_EXIST(2, "用户不存在"),
    PWD_ERR(3, "密码错误"),
    USER_BANNED(4, "当前用户已被封禁"),
    PERMISSION_DENIED(5, "权限不足"),
    TOILET_NOT_EXIST(100, "厕所不存在"),
    FEEDBACK_NOT_EXIST(200, "反馈不存在"),
    ROOM_EXIST(300, "坑位已存在"),
    ROOM_NOT_EXIST(301, "坑位不存在"),
    DEVICE_MATCH_FAILED(400, "设备匹配失败"),
    VALIDATE_FAILED(1001, "验证失败"),
    UNAUTHORIZED(1002, "未授权"),
    FORBIDDEN(1003, "Forbidden"),
    TOKEN_MISSING(1004, "未检测到token"),
    CANNOT_UPDATE_SELF_PERMISSION(1005, "不能修改自己的权限");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
