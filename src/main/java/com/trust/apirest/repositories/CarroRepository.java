package com.trust.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.apirest.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
  
}
