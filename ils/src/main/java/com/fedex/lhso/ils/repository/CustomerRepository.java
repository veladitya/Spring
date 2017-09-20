package com.fedex.lhso.ils.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fedex.lhso.ils.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
