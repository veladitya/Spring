package com.fedex.lhso.ils.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.lhso.ils.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findById(Integer itemId);
}
