package org.zucc.springbootsample.utils;

import org.springframework.beans.BeanUtils;
import org.zucc.springbootsample.dto.PersonDTO;
import org.zucc.springbootsample.dao.PersonDAO;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDAOconverttoDTO {
    public static PersonDTO convertoTO(PersonDAO personDAO){

        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(personDAO,personDTO);
        return  personDTO;

    }

    public static  List<PersonDTO> converToDT(List<PersonDAO> personDAOList){
        return personDAOList.stream().map(x->
                convertoTO(x)
        ).collect(Collectors.toList());
    }
}
