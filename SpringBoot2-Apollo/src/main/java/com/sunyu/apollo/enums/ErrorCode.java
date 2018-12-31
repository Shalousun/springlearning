package com.sunyu.apollo.enums;

/**
 * Error code enum
 * @author yu on 2018/11/12.
 */
public enum ErrorCode {

    SUCCESS("0000", "success"),

    PARAM_EMPTY("1001", "必选参数为空"),

    PARAM_ERROR("1002", "参数格式错误"),

    UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试....");

    private String code;

    private String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }


    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.desc;
    }
}
