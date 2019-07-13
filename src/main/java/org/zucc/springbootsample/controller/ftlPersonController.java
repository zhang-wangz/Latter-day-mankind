package org.zucc.springbootsample.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zucc.springbootsample.dao.mapper.Weapons;
import org.zucc.springbootsample.dto.PersonDTO;
import org.zucc.springbootsample.enums.PersonEnums;
import org.zucc.springbootsample.enums.PersonProfessionalEnums;
import org.zucc.springbootsample.enums.PersonResultEnum;
import org.zucc.springbootsample.exception.PersonException;
import org.zucc.springbootsample.form.PersonFrom;
import org.zucc.springbootsample.repository.PersonbaseRepository;
import org.zucc.springbootsample.service.PersonService;
import org.zucc.springbootsample.service.WeaponService;
import org.zucc.springbootsample.utils.EnumUtils;
import org.zucc.springbootsample.utils.KeyUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
@Slf4j
public class ftlPersonController {



    @Autowired
    private PersonService personService;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private PersonbaseRepository personbaseRepository;



    /**
     *
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     *
     */
    @GetMapping("/list")
    public ModelAndView list( @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              Map<String, Object> map){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<PersonDTO> personDTOPage = personService.findList(pageRequest);
        List<Weapons> weaponsList = weaponService.findAll();
        map.put("personDTOPage", personDTOPage);
        map.put("weaponsList",weaponsList);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("person/list", map);

    }


    @GetMapping("/listedit")
    public ModelAndView listedit( @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              Map<String, Object> map){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<PersonDTO> personDTOPage = personService.findList(pageRequest);
        List<Weapons> weaponsList = weaponService.findAll();
        map.put("weaponsList",weaponsList);
        map.put("personDTOPage", personDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("person/listedit", map);

    }




    @GetMapping("/kill/{id}")
    public  ModelAndView kill(@PathVariable(value = "id") String personId,
                                Map<String, Object> map){
        try {
            PersonDTO personDTO = personService.findOne(personId);
            personService.cancel(personDTO);

        }catch (PersonException e){
            map.put("msg",e.getMessage());
            map.put("url","/ch/person/listedit");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", PersonResultEnum.CANCEL_SUCCESS.getMsg());
        map.put("url", "/ch/person/listedit");
        return new ModelAndView("common/success",map);
    }


    @GetMapping("/delete")
    public  ModelAndView delete(@RequestParam(value = "personId") String personId,
                              Map<String, Object> map){
        try {
            PersonDTO personDTO = personService.findOne(personId);
            personbaseRepository.deleteById(personDTO.getUserid());

        }catch (PersonException e){
            map.put("msg",e.getMessage());
            map.put("url","/ch/person/listedit");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", PersonResultEnum.DELETE_SUCCESS.getMsg());
        map.put("url", "/ch/person/listedit");
        return new ModelAndView("common/success",map);
    }




    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "personId")String personId,
                                Map<String,Object>map) {
        PersonDTO personDTO = new PersonDTO();
        try {
            personDTO = personService.findOne(personId);
        } catch (PersonException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ch/person/detail");
            return new ModelAndView("common/error", map);
        }

        map.put("personDTO", personDTO);
        return new ModelAndView("person/detail", map);
    }



    @GetMapping("/index")
    public  ModelAndView index(@RequestParam(value = "personId" ,required = false) String id,
                                Map<String,Object> map) {
        List<Weapons> weaponsList = weaponService.findAll();
        PersonDTO personDTO = new PersonDTO();
        if(!StringUtils.isEmpty(id)){

            personDTO = personService.findOne(id);
            map.put("personInfo",personDTO);

        }
        map.put("personJob", EnumUtils.EnumsList(PersonProfessionalEnums.class));
        map.put("personPersonStatus",EnumUtils.EnumsList(PersonEnums.class));
        map.put("weaponsList",weaponsList);

        return new ModelAndView("person/index",map);

    }



    @PostMapping("/save")
    public ModelAndView save(@Valid PersonFrom personFrom,
                             BindingResult bindingResult,
                             Map<String,Object>map) {


        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/ch/person/index");
            return new ModelAndView("common/error", map);
        }

        PersonDTO personDTO = new PersonDTO();

        try {
            //如果Id为空, 说明是新增
            if (!StringUtils.isEmpty(personFrom.getUserid())) {

                personDTO = personService.findOne(personFrom.getUserid());

            } else {
                personFrom.setUserid(KeyUtil.getUniqueKey());
            }

            BeanUtils.copyProperties(personFrom, personDTO);

            personService.create(personDTO);


        } catch (PersonException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ch/person/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/ch/person/listedit");
        return new ModelAndView("common/success", map);

    }









}
