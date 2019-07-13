package org.zucc.springbootsample.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.zucc.springbootsample.dao.mapper.Weapons;
import org.zucc.springbootsample.enums.WeaponsResultEnum;
import org.zucc.springbootsample.exception.WeaponExption;
import org.zucc.springbootsample.repository.WeaponBaseRepository;
import org.zucc.springbootsample.service.WeaponService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WeaponServiceImpl implements WeaponService {

    @Autowired
    private WeaponBaseRepository weaponBaseRepository;

    @Override
    public List<Weapons> findAll() {
        return weaponBaseRepository.findAll();
    }

    @Override
    public Page<Weapons> findList(Pageable pageable) {
        Page<Weapons> WeaponsPage = weaponBaseRepository.findAll(pageable);

        List<Weapons> WeaponsList = WeaponsPage.getContent();

        return  new PageImpl<>(WeaponsList,pageable,WeaponsPage.getTotalElements());
    }

    @Override
    public Weapons findOne(String weaponId) {
        Weapons Weapons = weaponBaseRepository.findById(weaponId).orElse(null);

        if(Weapons == null){
            throw  new WeaponExption(WeaponsResultEnum.FAIL_BLANK);
        }
        return Weapons;
    }

    @Override
    public Weapons delete(String weaponsId) {
        Weapons Weapons = weaponBaseRepository.findById(weaponsId).orElse(null);
        if(Weapons != null) {
            weaponBaseRepository.delete(Weapons);
        }
        return Weapons;

    }

    @Override
    public Weapons create(Weapons weapons) {

        weaponBaseRepository.save(weapons);

        return weapons;
    }

    @Override
    public List<String> findAllWeaponsName() {

        List<String> weaponsNameList= weaponBaseRepository.findAll().stream().map(e->
                e.getName()
        ).collect(Collectors.toList());

        return weaponsNameList;
    }

    @Override
    public void increaseStock(Weapons Weapons) {
        Weapons Weapons1 = weaponBaseRepository.findById(Weapons.getId()).orElse(null);
        if(Weapons1 == null){
            throw  new WeaponExption(WeaponsResultEnum.FAIL_BLANK);
        }

        if(Weapons1.getWeaponsStock() == 200) {
            throw new WeaponExption(WeaponsResultEnum.STOCK_FULL);
        }else if(Weapons1.getWeaponsStock() < 200){
            Weapons1.setWeaponsStock(Weapons.getWeaponsStock() + 1);
        }

    }

    @Override
    public void decreaseStock(Weapons Weapons) {
        Weapons Weapons1 = weaponBaseRepository.findById(Weapons.getId()).orElse(null);
        if(Weapons1 == null){
            throw  new WeaponExption(WeaponsResultEnum.FAIL_BLANK);
        }

        if(Weapons1.getWeaponsStock() == 0) {
            throw new WeaponExption(WeaponsResultEnum.STOCK_BLANK);
        }else if(Weapons1.getWeaponsStock() > 0){
            Weapons1.setWeaponsStock(Weapons.getWeaponsStock() - 1);
        }

    }
}
