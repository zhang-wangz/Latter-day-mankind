package org.zucc.springbootsample.form;

import lombok.Data;
import org.zucc.springbootsample.enums.PersonEnums;
import org.zucc.springbootsample.enums.PersonWeaponHaveEnum;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


/**
 *
 * 这里之前写的时候和数据库映射写成了大驼峰命名方式
 * 需要修改
 */
@Data
public class PersonFrom {

    private String  Userid;

//    @NotEmpty(message = "姓名不可为空")
    private String Name;

//    @NotEmpty(message = "职业不可为空")
    private Integer professionalStatus;


//    @NotEmpty(message = "地址不可为空")
    private String Address;

//    @Column(name = "weaponsid")
    private String weaponsId;


}
