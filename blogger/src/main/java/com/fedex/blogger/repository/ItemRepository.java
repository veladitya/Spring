package com.fedex.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.blogger.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findById(Integer itemId);
}
