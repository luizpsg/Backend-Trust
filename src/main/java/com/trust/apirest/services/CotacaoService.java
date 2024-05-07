package com.trust.apirest.services;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.trust.apirest.entities.Carro;
import com.trust.apirest.entities.Cliente;
import com.trust.apirest.entities.Cotacao;
import com.trust.apirest.repositories.CarroRepository;
import com.trust.apirest.repositories.ClienteRepository;
import com.trust.apirest.repositories.CotacaoRepository;
import com.trust.apirest.services.exceptions.DatabaseException;
import com.trust.apirest.services.exceptions.ResourceNotFoundException;

@Service
public class CotacaoService {

  @Autowired
  private CotacaoRepository cotacaoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private CarroRepository carroRepository;

  public Cotacao findCotacaoByClienteIdAndCotacaoId(Long clienteId, Long cotacaoId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));
    return cliente.getCotacoes().stream()
        .filter(cotacao -> cotacao.getId().equals(cotacaoId))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Cotação não encontrada com ID: " + cotacaoId));
  }

  // @Transactional
  // public Cotacao insertWithCliente(Long clienteId, Long carroId, String
  // nomeSeguradora, LocalDate dataCotacao, Double valorCotacao,
  // MultipartFile arquivo, Integer maxParcelasPix, Integer maxParcelasBoleto,
  // Integer maxParcelasCartao, Integer maxParcelasDebitoConta, Integer
  // maxParcelasCartaoEspecial) throws IOException {
  // Cliente cliente = clienteRepository.findById(clienteId)
  // .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com
  // ID: " + clienteId));

  // Carro carro = carroRepository.findById(carroId)
  // .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com
  // ID: " + carroId));

  // Cotacao cotacao = new Cotacao();
  // cotacao.setCliente(cliente);
  // cotacao.setCarro(carro);
  // cotacao.setNomeSeguradora(nomeSeguradora);
  // cotacao.setDataCotacao(dataCotacao);
  // cotacao.setValorCotacao(valorCotacao);
  // cotacao.setMaxParcelasPix(maxParcelasPix);
  // cotacao.setMaxParcelasBoleto(maxParcelasBoleto);
  // cotacao.setMaxParcelasCartao(maxParcelasCartao);
  // cotacao.setMaxParcelasDebitoConta(maxParcelasDebitoConta);
  // cotacao.setMaxParcelasCartaoEspecial(maxParcelasCartaoEspecial);

  // if (arquivo != null && !arquivo.isEmpty()) {
  // cotacao.setArquivoCotacao(arquivo.getBytes());
  // }

  // return cotacaoRepository.save(cotacao);
  // }

  @Transactional
  public Cotacao insertWithCliente(Long clienteId, String nomeSeguradora, LocalDate dataCotacao,
      Double valorCotacao,
      Integer maxParcelasPix, Integer maxParcelasBoleto, Integer maxParcelasCartao, Integer maxParcelasDebitoConta,
      Integer maxParcelasCartaoEspecial) {

    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId));

    Cotacao cotacao = new Cotacao();
    cotacao.setCliente(cliente);
    cotacao.setNomeSeguradora(nomeSeguradora);
    cotacao.setDataCotacao(dataCotacao);
    cotacao.setValorCotacao(valorCotacao);
    cotacao.setMaxParcelasPix(maxParcelasPix);
    cotacao.setMaxParcelasBoleto(maxParcelasBoleto);
    cotacao.setMaxParcelasCartao(maxParcelasCartao);
    cotacao.setMaxParcelasDebitoConta(maxParcelasDebitoConta);
    cotacao.setMaxParcelasCartaoEspecial(maxParcelasCartaoEspecial);

    return cotacaoRepository.save(cotacao);
  }

  // Outros métodos de serviço para operações adicionais como deletar cotação,
  // etc.

  @Transactional
  public void delete(Long id) {
    try {
      if (cotacaoRepository.existsById(id)) {
        cotacaoRepository.deleteById(id);
      } else {
        throw new ResourceNotFoundException(id);
      }
    } catch (Exception e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  @Transactional
  public Cotacao update(Long id, Cotacao obj) {
    Cotacao entity = cotacaoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cotação não encontrada com ID: " + id));
    updateData(entity, obj);
    return cotacaoRepository.save(entity);
  }

  private void updateData(Cotacao entity, Cotacao obj) {
    if (obj.getNomeSeguradora() != null) {
      entity.setNomeSeguradora(obj.getNomeSeguradora());
    } else {
      entity.setNomeSeguradora(entity.getNomeSeguradora());
    }

    if (obj.getDataCotacao() != null) {
      entity.setDataCotacao(obj.getDataCotacao());
    } else {
      entity.setDataCotacao(entity.getDataCotacao());
    }

    if (obj.getValorCotacao() != null) {
      entity.setValorCotacao(obj.getValorCotacao());
    } else {
      entity.setValorCotacao(entity.getValorCotacao());
    }

    if (obj.getMaxParcelasPix() != null) {
      entity.setMaxParcelasPix(obj.getMaxParcelasPix());
    } else {
      entity.setMaxParcelasPix(entity.getMaxParcelasPix());
    }

    if (obj.getMaxParcelasBoleto() != null) {
      entity.setMaxParcelasBoleto(obj.getMaxParcelasBoleto());
    } else {
      entity.setMaxParcelasBoleto(entity.getMaxParcelasBoleto());
    }

    if (obj.getMaxParcelasCartao() != null) {
      entity.setMaxParcelasCartao(obj.getMaxParcelasCartao());
    } else {
      entity.setMaxParcelasCartao(entity.getMaxParcelasCartao());
    }

    if (obj.getMaxParcelasDebitoConta() != null) {
      entity.setMaxParcelasDebitoConta(obj.getMaxParcelasDebitoConta());
    } else {
      entity.setMaxParcelasDebitoConta(entity.getMaxParcelasDebitoConta());
    }

    if (obj.getMaxParcelasCartaoEspecial() != null) {
      entity.setMaxParcelasCartaoEspecial(obj.getMaxParcelasCartaoEspecial());
    } else {
      entity.setMaxParcelasCartaoEspecial(entity.getMaxParcelasCartaoEspecial());
    }
  }

  @Transactional
  public Cotacao updateArquivoCotacao(Long id, MultipartFile arquivo) throws IOException {
    Cotacao entity = cotacaoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cotação não encontrada com ID: " + id));

    if (arquivo != null && !arquivo.isEmpty()) {
      entity.setArquivoCotacao(arquivo.getBytes());
    } else {
      entity.setArquivoCotacao(entity.getArquivoCotacao());
    }

    return cotacaoRepository.save(entity);
  }
}
