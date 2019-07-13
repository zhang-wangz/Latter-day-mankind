package org.zucc.springbootsample.exception;


import org.zucc.springbootsample.enums.WeaponsResultEnum;
import org.zucc.springbootsample.enums.WeaponsTypeEnum;

public class WeaponExption extends PersonException {

    private  Integer code;

    public WeaponExption(Integer code,String msg){

        super(msg);
        this.code = code;
    }

    public WeaponExption(WeaponsResultEnum weaponsResultEnum){

        super(weaponsResultEnum.getMsg());
        this.code = weaponsResultEnum.getCode();
    }

    public WeaponExption(String msg){
        super(msg);
    }
}
