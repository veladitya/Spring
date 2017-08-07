package com.fedex.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.blogger.entity.Blog;

public interface BlogRepository extends CrudRepository<Blog, Integer> {

    List<Blog> findByName(String name);
}
