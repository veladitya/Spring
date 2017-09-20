package com.fedex.lhso.ils.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.lhso.ils.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findByName(String name);
    List<Role> findAllByOrderByNameAsc();    
}
