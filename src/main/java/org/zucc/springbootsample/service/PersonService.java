package org.zucc.springbootsample.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zucc.springbootsample.dao.PersonDAO;
import org.zucc.springbootsample.dao.mapper.Weapons;
import org.zucc.springbootsample.dto.PersonDTO;

import java.util.List;


public interface PersonService {

    List<PersonDAO> findAll();


    PersonDAO create(PersonDAO personDAO);


    Page<PersonDTO> findList(Pageable pageable);


    PersonDTO findOne(String personId);


    PersonDTO cancel(PersonDTO personDTO);


    PersonDTO create(PersonDTO personDTO);


}
