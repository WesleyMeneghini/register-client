package com.brasilprev.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasilprev.desafio.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
