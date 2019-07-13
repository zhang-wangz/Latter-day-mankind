package org.zucc.springbootsample.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zucc.springbootsample.dao.mapper.Weapons;


import java.util.List;

public interface WeaponService {
    List<Weapons> findAll();


    Page<Weapons> findList(Pageable pageable);


    Weapons findOne(String weaponId);


    Weapons delete(String weaponsId);


    Weapons create(Weapons Weapons);

    List<String> findAllWeaponsName();



    void increaseStock(Weapons Weapons);

    void decreaseStock(Weapons Weapons);


}
