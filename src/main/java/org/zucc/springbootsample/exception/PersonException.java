package org.zucc.springbootsample.exception;

import org.zucc.springbootsample.enums.PersonResultEnum;

public class PersonException extends  RuntimeException{

    private  Integer code;

    public PersonException(Integer code,String msg){

        super(msg);
        this.code = code;
    }

    public PersonException(PersonResultEnum personResultEnum){
        super(personResultEnum.getMsg());
        this.code = personResultEnum.getCode();
    }

    public PersonException(String msg) {
        super(msg);
    }
}
