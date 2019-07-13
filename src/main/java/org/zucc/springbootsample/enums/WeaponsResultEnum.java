package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum  WeaponsResultEnum implements EnumCode{

    CREATE_SUCCESS(0,"创建成功"),
    FAIL_BLANK(1,"武器为空"),
    WEAPON_ID_NOT_EXIST(2,"该id为空"),
    CANCEL_SUCCESS(3,"删除成功"),
    NEW_WEAPON_FORBID_CANCEL(4,"新创建武器禁止删除"),
    STOCK_BLANK(5,"库存已空"),
    STOCK_FULL(6,"库存达到上限"),

            ;


    private Integer code;
    private String msg;

    WeaponsResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
