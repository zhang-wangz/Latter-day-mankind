package org.zucc.springbootsample.dao.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.zucc.springbootsample.enums.WeaponsTypeEnum;
import org.zucc.springbootsample.utils.EnumUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Timestamp;


@Data
@Entity
public class Weapons {

    @Id
    private  String id;

    private String Name;

    private  Integer price;

    private String description;


    @Column(name = "weaponsstock")
    private Integer weaponsStock;

    //private Integer weaponStatus = WeaponsStatusEnum.UP.getCode();

    @Column(name = "weaponstype")
    private Integer weaponsType;


    @Column(name = "uploadtime")
    private Timestamp uploadTime = new Timestamp(System.currentTimeMillis());

    @Column(name = "updatetime")
    private Timestamp updateTime;


    @JsonIgnore
    public WeaponsTypeEnum getWeaponsTypeEnum(){

        return EnumUtils.getByCode(weaponsType,WeaponsTypeEnum.class);
    }

}
