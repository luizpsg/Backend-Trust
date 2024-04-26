package com.trust.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trust.apirest.entities.Carro;
import com.trust.apirest.entities.Cliente;
import com.trust.apirest.repositories.CarroRepository;
import com.trust.apirest.repositories.ClienteRepository;
import com.trust.apirest.services.exceptions.DatabaseException;
import com.trust.apirest.services.exceptions.ResourceNotFoundException;

@Service
public class CarroService {

  @Autowired
  private CarroRepository repository;

  @Autowired
  private ClienteRepository clienteRepository;

  
  public Carro findCarroByClienteIdAndCarroId(Long clienteId, Long carroId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getCarros().stream()
        .filter(carro -> carro.getId().equals(carroId))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + carroId));
  }

  public Carro findCarroByClienteIdAndCarroPlaca(Long clienteId, String placa) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getCarros().stream()
        .filter(carro -> carro.getPlaca().equals(placa))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com placa: " + placa));
  }

  @Transactional
  public Carro insertWithCliente(Long clienteId, Carro carro) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    carro.setCliente(cliente);
    return repository.save(carro);
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
  public Carro update(Long id, Carro obj) {
    Carro entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    updateData(entity, obj);
    return repository.save(entity);
  }

  private void updateData(Carro entity, Carro obj) {
    if (obj.getMarca() != null) {
      entity.setMarca(obj.getMarca());
    } else {
      entity.setMarca(entity.getMarca());
    }
    
    if (obj.getModelo() != null) {
      entity.setModelo(obj.getModelo());
    }
    if (obj.getPlaca() != null) {
      entity.setPlaca(obj.getPlaca());
    }
    if (obj.getAnoModelo() != null) {
      entity.setAnoModelo(obj.getAnoModelo());
    }
    if (obj.getCor() != null) {
      entity.setCor(obj.getCor());
    }
  }

  @Transactional
  public void reassignCarToClient(Long carroId, Long clienteId) {
    Carro carro = repository.findById(carroId)
        .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + carroId));
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));

    carro.setCliente(cliente);  // Atualiza o cliente associado ao carro
    repository.save(carro);  // Salva a atualização no banco de dados
}


}
