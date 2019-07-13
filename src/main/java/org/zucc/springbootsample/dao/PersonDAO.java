package org.zucc.springbootsample.dao;


import lombok.Data;
import org.zucc.springbootsample.enums.PersonEnums;
import org.zucc.springbootsample.enums.PersonWeaponHaveEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class PersonDAO {

    @Id
    private String Userid;


    private String Name;

    @Column(name = "professionalstatus")
    private int professionalStatus;


    private String Address;

    @Column(name = "weaponsid")
    private String weaponsId;


    private Integer personstatus = PersonEnums.New.getCode();


    private Date birthdate=new Date(System.currentTimeMillis());


    private Date deathdate;


}
