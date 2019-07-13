package org.zucc.springbootsample.form;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;


@Data
public class WeaponsForm {

    private String weaponId;

//    @NotEmpty(message = "Name不可空")
    private String Name;

//    @NotEmpty(message = "价格不可空")
    private  Integer price;

    private String description;

//    @NotEmpty(message = "库存不可空")
    @Column(name = "weaponsstock")
    private Integer weaponsStock;

//    @NotEmpty(message = "武器类型不可空")
    @Column(name = "weaponstype")
    private Integer weaponsType;

}
