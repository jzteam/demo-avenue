package com.example.demoavenue.enums;

import java.util.Arrays;

public enum EnumAvenueMenuType {

    MENU_TYPE_ROOT(0, "根菜单"),
    MENU_TYPE_FIRST(1, "一级菜单"),
    MENU_TYPE_SECOND(2, "二级菜单"),
    MENU_TYPE_THIRD(3, "三级菜单");

    private final int code;
    private final String name;

    EnumAvenueMenuType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EnumAvenueMenuType valueOf(int code) {
        return Arrays.stream(values()).filter((x) -> x.getCode() == code)
                .findFirst().orElse(null);
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public EnumAvenueMenuType getEnum(Integer code){
        if(code == null){
            return null;
        }
        for(EnumAvenueMenuType t : EnumAvenueMenuType.values()){
            if(code.equals(t.getCode())){
                return t;
            }
        }
        return null;
    }

}
