package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum PersonWeaponHaveEnum implements EnumCode {
    HAVE(1,"持有武器且人类武器的具有编号，1为替代值"),
    NOT_HAVE(0,"没有武器"),
            ;

    private Integer code;
    private String msg;

    PersonWeaponHaveEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
