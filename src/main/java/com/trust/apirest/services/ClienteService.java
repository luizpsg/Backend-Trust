package com.trust.apirest.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trust.apirest.entities.Carro;
import com.trust.apirest.entities.Cliente;
import com.trust.apirest.entities.Cotacao;
import com.trust.apirest.entities.Endereco;
import com.trust.apirest.repositories.ClienteRepository;
import com.trust.apirest.services.exceptions.DatabaseException;
import com.trust.apirest.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  public List<Cliente> findAll() {
    return repository.findAll();
  }

  public Cliente findById(Long id) {
    Optional<Cliente> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public List<Cliente> findByCpfStartingWith(String cpf) {
    return repository.findByCpfStartingWith(cpf);
  }

  public List<Cliente> findClientesByMonthOfBirth(int month) {
    return repository.findClientesByMonthOfBirth(month);
  }

  public List<Cliente> findCurrentMonthBirthdays() {
    int currentMonth = LocalDate.now().getMonthValue();
    return repository.findClientesByMonthOfBirth(currentMonth);
  }

  public List<Carro> findCarrosByClienteId(Long clienteId) {
    Cliente cliente = repository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getCarros();
  }

  public List<Endereco> findEnderecosByClienteId(Long clienteId) {
    Cliente cliente = repository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getEnderecos();
  }

  public List<Cotacao> findCotacoesByClienteId(Long clienteId) {
    Cliente cliente = repository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getCotacoes();
  }

  /*
   * public Cliente findByCpf(String cpf) {
   * Optional<Cliente> obj = repository.findByCpf(cpf);
   * return obj.orElseThrow(() -> new ResourceNotFoundException(cpf));
   * }
   */

  @Transactional
  public Cliente insert(Cliente obj) {
    return repository.save(obj);
  }

  @Transactional
  public void delete(Long id) {
    try {
      if (repository.existsById(id)) {
        repository.deleteById(id);
      } else {
        throw new ResourceNotFoundException(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  @Transactional
  public Cliente update(Long id, Cliente obj) {
    Cliente entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    updateData(entity, obj);
    return repository.save(entity);
  }

  private void updateData(Cliente entity, Cliente obj) {
    if (obj.getNome() != null) {
      entity.setNome(obj.getNome());
    }
    if (obj.getCpf() != null) {
      entity.setCpf(obj.getCpf());
    }
    if (obj.getTelefone() != null) {
      entity.setTelefone(obj.getTelefone());
    }
    if (obj.getDataNascimento() != null) {
      entity.setDataNascimento(obj.getDataNascimento());
    }
    if (obj.getEmail() != null) {
      entity.setEmail(obj.getEmail());
    }
  }

}
