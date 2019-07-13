package org.zucc.springbootsample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.zucc.springbootsample.enums.PersonEnums;
import org.zucc.springbootsample.enums.PersonProfessionalEnums;
import org.zucc.springbootsample.enums.PersonWeaponHaveEnum;
import org.zucc.springbootsample.utils.EnumUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
public class PersonDTO {

    @Id
    private String Userid;


    private String Name;


    private Integer professionalStatus;


    private String Address;


    private Integer personstatus = PersonEnums.New.getCode();


    private Timestamp birthdate = new Timestamp(System.currentTimeMillis());


    private Timestamp deathdate;


    @Column(name = "weaponsid")
    private String WeaponsId;

    @JsonIgnore
    public PersonEnums getPersonEnum() {

        return EnumUtils.getByCode(personstatus, PersonEnums.class);
    }


    @JsonIgnore
    public PersonProfessionalEnums getPersonProfessionalEnums() {

        return EnumUtils.getByCode(professionalStatus, PersonProfessionalEnums.class);
    }


}