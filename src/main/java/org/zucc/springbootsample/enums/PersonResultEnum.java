package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum  PersonResultEnum implements EnumCode {
    CREATE_SUCCESS(0,"创建成功"),
    CREATE_FAIL_BLANK(1,"创建失败,人物为空"),
    PERSON_ID_NOT_EXIST(2,"该id为空"),
    CANCEL_SUCCESS(3,"抹除成功"),
    DELETE_SUCCESS(5,"该人物数据已删除"),
    NEW_PERSON_FORBID_CANCEL(4,"新生人类禁止删除"),
    PERSON_DEATH_WRONG(6,"该人类已经死亡"),
            ;


    private Integer code;
    private String msg;

    PersonResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
