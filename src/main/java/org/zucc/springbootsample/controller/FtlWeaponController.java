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
import org.zucc.springbootsample.enums.PersonProfessionalEnums;
import org.zucc.springbootsample.enums.WeaponsResultEnum;
import org.zucc.springbootsample.enums.WeaponsTypeEnum;
import org.zucc.springbootsample.exception.PersonException;
import org.zucc.springbootsample.exception.WeaponExption;
import org.zucc.springbootsample.form.PersonFrom;
import org.zucc.springbootsample.form.WeaponsForm;
import org.zucc.springbootsample.repository.WeaponBaseRepository;
import org.zucc.springbootsample.service.WeaponService;
import org.zucc.springbootsample.utils.EnumUtils;
import org.zucc.springbootsample.utils.KeyUtil;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/weapons")
@Slf4j
public class FtlWeaponController {

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private WeaponBaseRepository weaponBaseRepository;

    /**
     *
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     *
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Weapons> WeaponsPage = weaponService.findList(pageRequest);
        map.put("weaponsPage", WeaponsPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("weapons/list", map);
    }

    @GetMapping("/kill/{id}")
    public  ModelAndView kill(@PathVariable(value = "id") String weaponsId,
                              Map<String, Object> map){
        try {
            Weapons Weapons = weaponService.findOne(weaponsId);
            weaponBaseRepository.delete(Weapons);

        }catch (WeaponExption e){
            map.put("msg",e.getMessage());
            map.put("url","/ch/weapons/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", WeaponsResultEnum.CANCEL_SUCCESS.getMsg());
        map.put("url", "/ch/weapons/list");
        return new ModelAndView("common/success",map);
    }


    @GetMapping("/index")
    public  ModelAndView index(@RequestParam(value = "weaponsId" ,required = false) String id,
                               Map<String,Object> map) {
        if(id != null){
            Weapons weapons = weaponService.findOne(id);

            map.put("weaponsInfo",weapons);
        }

        map.put("weaponsList", EnumUtils.EnumsList(WeaponsTypeEnum.class));

        return new ModelAndView("weapons/index",map);

    }



    @PostMapping("/save")
    public ModelAndView save(@Valid WeaponsForm weaponsForm,
                             BindingResult bindingResult,
                             Map<String,Object>map) {


        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/ch/weapons/index");
            return new ModelAndView("common/error", map);
        }

        Weapons weapons = new Weapons();

        try {
            //如果Id为空, 说明是新增
            if (!StringUtils.isEmpty(weaponsForm.getWeaponId())) {

                weapons = weaponService.findOne(weaponsForm.getWeaponId());

            } else {
                weaponsForm.setWeaponId(KeyUtil.getUniqueKey());
            }

            BeanUtils.copyProperties(weaponsForm,weapons);
            weaponService.create(weapons);


        } catch (PersonException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ch/weapons/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/ch/weapons/list");
        return new ModelAndView("common/success", map);

    }



}
