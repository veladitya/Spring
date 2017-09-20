package com.fedex.lhso.ils.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.lhso.ils.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByName(String name);
}
