package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum WeaponsTypeEnum implements EnumCode {

    GUN(0,"枪类"),
    AXE(1,"斧类"),
            ;

    private Integer code;

    private String msg;

    WeaponsTypeEnum(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
