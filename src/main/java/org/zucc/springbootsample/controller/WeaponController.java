package org.zucc.springbootsample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zucc.springbootsample.dao.mapper.Weapons;
import org.zucc.springbootsample.form.WeaponsForm;
import org.zucc.springbootsample.service.WeaponService;
import org.zucc.springbootsample.utils.KeyUtil;
import org.zucc.springbootsample.utils.ResultUtil;
import org.zucc.springbootsample.vo.ResultVO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/weapons")
public class WeaponController {

    @Autowired
    private WeaponService weaponService;


    @PostMapping("/create")
    public ResultVO create(@Valid WeaponsForm weaponsForm,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        Weapons weapons = new Weapons();
        weapons.setId(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(weaponsForm,weapons);
        return ResultUtil.success(weaponService.create(weapons));
    }


    @GetMapping("/findall")
    public ResultVO<List<Weapons>> find() {
        List<Weapons> list = new ArrayList<>();
        List<Weapons> personDAOList = weaponService.findAll();
        for(Weapons Weapons:personDAOList){
            list.add(Weapons);
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/findlist")
    public ResultVO<Page<Weapons>> findList(Pageable pageable){

        return ResultUtil.success(weaponService.findList(pageable));
    }

}
