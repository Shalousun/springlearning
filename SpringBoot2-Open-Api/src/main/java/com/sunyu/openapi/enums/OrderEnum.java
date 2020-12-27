package com.sunyu.openapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author yu 2020/12/1.
 */
public enum OrderEnum {
    WAIT_PAY("0", "已支付"),

    PAID("1", "已支付"),

    EXPIRED("2", "已经失效");

    private String code;

    private String desc;

    OrderEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }


    public String getDesc() {
        return this.desc;
    }
}
