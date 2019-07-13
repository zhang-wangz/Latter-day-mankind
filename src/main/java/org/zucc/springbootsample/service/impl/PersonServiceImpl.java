package org.zucc.springbootsample.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zucc.springbootsample.dao.PersonDAO;
import org.zucc.springbootsample.dao.mapper.Weapons;
import org.zucc.springbootsample.dto.PersonDTO;
import org.zucc.springbootsample.enums.PersonEnums;
import org.zucc.springbootsample.enums.PersonResultEnum;
import org.zucc.springbootsample.exception.PersonException;
import org.zucc.springbootsample.repository.PersonbaseRepository;
import org.zucc.springbootsample.service.PersonService;
import org.zucc.springbootsample.utils.PersonDAOconverttoDTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonbaseRepository personbaseRepository;


    @Override
    public List<PersonDAO> findAll() {
        return personbaseRepository.findAll();
    }

    @Override
    public PersonDAO create(PersonDAO person) {
        return personbaseRepository.save(person);
    }

    @Override
    public Page<PersonDTO> findList(Pageable pageable) {

        Page<PersonDAO> personDAOPage = personbaseRepository.findAll(pageable);

        List<PersonDTO> personDTOList = PersonDAOconverttoDTO.converToDT(personDAOPage.getContent());

        return  new PageImpl<>(personDTOList,pageable,personDAOPage.getTotalElements());
    }

    @Override
    public  PersonDTO findOne(String personId) {
        PersonDAO personDAO = personbaseRepository.findById(personId).orElse(null);
        if(personDAO == null){
            throw  new PersonException(PersonResultEnum.PERSON_ID_NOT_EXIST);
        }
        PersonDTO personDTO = PersonDAOconverttoDTO.convertoTO(personDAO);
        return personDTO;
    }


   /**
    *
    * cancel 因为寻找后删除，所以该次删除对象一定不为空
    *
    *
    */


    @Override
    public PersonDTO cancel(PersonDTO personDTO){
        if(personDTO.getPersonstatus().intValue() == PersonEnums.New.getCode()){
            throw new PersonException(PersonResultEnum.NEW_PERSON_FORBID_CANCEL);
        }

        if(personDTO.getPersonstatus().intValue() == PersonEnums.DEATH.getCode()){
            throw new PersonException(PersonResultEnum.PERSON_DEATH_WRONG);
        }

        personDTO.setPersonstatus(PersonEnums.DEATH.getCode());
        personDTO.setDeathdate(new Timestamp(System.currentTimeMillis()));
        PersonDAO personDAO = personbaseRepository.findById(personDTO.getUserid()).orElse(null);
        BeanUtils.copyProperties(personDTO,personDAO);
        personbaseRepository.save(personDAO);

        return personDTO;
    }


    @Override
    public PersonDTO create(PersonDTO personDTO) {

        PersonDAO personDAO = personbaseRepository.findById(personDTO.getUserid()).orElse(null);

        if(personDTO == null){
            throw new PersonException(PersonResultEnum.CREATE_FAIL_BLANK);
        }

        if(personDAO == null){
            personDAO = new PersonDAO();
        }

        BeanUtils.copyProperties(personDTO,personDAO);

        personbaseRepository.save(personDAO);

        return personDTO;
    }

}

