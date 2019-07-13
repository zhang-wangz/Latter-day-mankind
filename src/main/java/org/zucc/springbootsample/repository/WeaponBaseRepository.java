package org.zucc.springbootsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zucc.springbootsample.dao.mapper.Weapons;


public interface WeaponBaseRepository  extends JpaRepository<Weapons,String> {

}
