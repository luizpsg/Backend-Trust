package com.trust.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trust.apirest.entities.Cliente;
import com.trust.apirest.entities.Endereco;
import com.trust.apirest.repositories.ClienteRepository;
import com.trust.apirest.repositories.EnderecoRepository;
import com.trust.apirest.services.exceptions.DatabaseException;
import com.trust.apirest.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

  @Autowired
  private EnderecoRepository repository;

  @Autowired
  private ClienteRepository clienteRepository;

  public Endereco findEnderecoByClienteIdAndEnderecoId(Long clienteId, Long enderecoId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getEnderecos().stream()
        .filter(endereco -> endereco.getId().equals(enderecoId))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado com ID: " + enderecoId));
  }

  @Transactional
  public Endereco insertWithCliente(Long clienteId, Endereco endereco) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    endereco.setCliente(cliente);
    return repository.save(endereco);
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
  public Endereco update(Long id, Endereco obj) {
    Endereco entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    updateData(entity, obj);
    return repository.save(entity);
  }

  private void updateData(Endereco entity, Endereco obj) {
    if (obj.getRua() != null) {
      entity.setRua(obj.getRua());
    }
    if (obj.getNumero() != null) {
      entity.setNumero(obj.getNumero());
    }
    if (obj.getBairro() != null) {
      entity.setBairro(obj.getBairro());
    }
    if (obj.getCidade() != null) {
      entity.setCidade(obj.getCidade());
    }
    if (obj.getEstado() != null) {
      entity.setEstado(obj.getEstado());
    }
    if (obj.getCep() != null) {
      entity.setCep(obj.getCep());
    }
    if (obj.getComplemento() != null) {
      entity.setComplemento(obj.getComplemento());
    }
  }

  @Transactional
  public void reassignEndToClient(Long enderecoId, Long clienteId) {
    Endereco endereco = repository.findById(enderecoId)
        .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado com ID: " + enderecoId));
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));

    endereco.setCliente(cliente);  // Atualiza o cliente associado ao Endereco
    repository.save(endereco);  // Salva a atualização no banco de dados
}

}
