package com.trust.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.apirest.entities.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long>{
  
}
