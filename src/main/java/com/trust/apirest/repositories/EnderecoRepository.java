package com.trust.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.apirest.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
  
}
