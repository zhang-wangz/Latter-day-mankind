package org.zucc.springbootsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zucc.springbootsample.dao.PersonDAO;


public abstract interface PersonbaseRepository extends JpaRepository<PersonDAO,String> {



}
