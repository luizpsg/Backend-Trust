package com.trust.apirest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trust.apirest.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

  Optional<Cliente> findByCpf(String cpf);

  @Query("SELECT c FROM Cliente c WHERE c.cpf LIKE :cpf%")
  List<Cliente> findByCpfStartingWith(String cpf);

  @Query("SELECT c FROM Cliente c WHERE MONTH(c.dataNascimento) = :month")
  List<Cliente> findClientesByMonthOfBirth(int month);

}
