package com.elkabani.db_demo.repositories;

import com.elkabani.db_demo.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}