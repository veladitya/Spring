package com.fedex.lhso.ils.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.lhso.ils.entity.Blog;

public interface BlogRepository extends CrudRepository<Blog, Integer> {

    List<Blog> findByName(String name);
}
