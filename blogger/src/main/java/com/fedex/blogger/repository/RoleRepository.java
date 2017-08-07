package com.fedex.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.blogger.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findByName(String name);
    List<Role> findAllByOrderByNameAsc();    
}
