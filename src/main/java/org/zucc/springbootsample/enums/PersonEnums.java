package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum PersonEnums implements EnumCode{

    New(0,"新人类"),
    OlD(1,"旧人类"),
    DEATH(2,"死亡人类"),
            ;

    private Integer code;
    private String msg;

    PersonEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
