package com.bootcamp.dscliente.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.dscliente.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

}
