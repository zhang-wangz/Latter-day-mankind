package org.zucc.springbootsample.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zucc.springbootsample.dao.PersonDAO;
import org.zucc.springbootsample.dto.PersonDTO;
import org.zucc.springbootsample.form.PersonFrom;
import org.zucc.springbootsample.service.PersonService;
import org.zucc.springbootsample.utils.PersonDAOconverttoDTO;
import org.zucc.springbootsample.utils.PersonFormConvertTODTO;
import org.zucc.springbootsample.utils.ResultUtil;
import org.zucc.springbootsample.vo.ResultVO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping("/create")
    public ResultVO create(@Valid PersonFrom personFrom,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(personFrom,personDTO);
        return ResultUtil.success(personService.create(personDTO));
    }


    @GetMapping("/findall")
    public ResultVO<List<PersonDTO>> find() {
        List<PersonDTO> list = new ArrayList<>();
        List<PersonDAO> personDAOList = personService.findAll();
        for(PersonDAO personDAO:personDAOList){
            list.add(PersonDAOconverttoDTO.convertoTO(personDAO));
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/findlist")
    public ResultVO<Page<PersonDAO>> findList(Pageable pageable){

        return ResultUtil.success(personService.findList(pageable));
    }

}
