package com.example.demoavenue.enums;

import cn.jzteam.avenue.swift.enums.IEnumBizError;

import java.util.Arrays;

public enum EnumBizError implements IEnumBizError {
    BIZ_PARAMS_EMPTY(1, "error.biz.code.1");

    private final int code;
    private final String message;

    EnumBizError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EnumBizError valueOf(int code) {
        return Arrays.stream(values()).filter((x) -> x.getCode() == code)
                .findFirst().orElse(null);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
