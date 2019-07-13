package org.zucc.springbootsample.enums;


import lombok.Getter;

@Getter
public enum PersonProfessionalEnums implements EnumCode{

    HOBO(0,"无业游民"),
    STUDENT(1,"学生"),
    TEACHER(2,"老师"),
    PARENTS(3,"家长"),
    ;


    private Integer code;

    private String msg;

    PersonProfessionalEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
