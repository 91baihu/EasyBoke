package com.easyboke.entity.enums;

public enum ResponseCodeEnum {
    CODE_200(200, "成功"),
    CODE_500(500, "服务器错误"),

    CODE_400(400, "参数错误"),

    CODE_401(401, "登录失败"),
    CODE_403(403, "没有权限"),

    CODE_404(404, "资源不存在"),

    CODE_600(600, "参数类型错误"),

    CODE_601(601, "主键冲突");

    private Integer code;
    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

