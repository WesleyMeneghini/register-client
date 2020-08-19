package com.brasilprev.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasilprev.desafio.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Boolean existsByCpf(String cpf);
}
