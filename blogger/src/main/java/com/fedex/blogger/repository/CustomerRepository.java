package com.fedex.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.blogger.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
