package com.trust.apirest.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cotacao")
public class Cotacao implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome_seguradora")
  private String nomeSeguradora;

  @Column(name = "data_cotacao")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataCotacao;

  @Column(name = "valor_cotacao")
  private Double valorCotacao;

  @JsonIgnore
  @Lob
  @Column(name = "arquivo_cotacao", columnDefinition = "MEDIUMBLOB")
  private byte[] arquivoCotacao;

  @Column(name = "max_parcelas_pix")
  private Integer maxParcelasPix;

  @Column(name = "max_parcelas_boleto")
  private Integer maxParcelasBoleto;

  @Column(name = "max_parcelas_cartao")
  private Integer maxParcelasCartao;

  @Column(name = "max_parcelas_debito_conta")
  private Integer maxParcelasDebitoConta;

  @Column(name = "max_parcelas_cartao_especial")
  private Integer maxParcelasCartaoEspecial;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "carro_id")
  private Carro carro;

  public Cotacao() {
  }

  public Cotacao(Long id, String nomeSeguradora, LocalDate dataCotacao, Double valorCotacao, byte[] arquivoCotacao,
      Cliente cliente, Carro carro,Integer maxParcelasPix,
      Integer maxParcelasBoleto,
      Integer maxParcelasCartao, Integer maxParcelasDebitoConta, Integer maxParcelasCartaoEspecial) {
    this.id = id;
    this.nomeSeguradora = nomeSeguradora;
    this.dataCotacao = dataCotacao;
    this.valorCotacao = valorCotacao;
    this.arquivoCotacao = arquivoCotacao;
    this.cliente = cliente;
    this.carro = carro;
    this.maxParcelasPix = maxParcelasPix;
    this.maxParcelasBoleto = maxParcelasBoleto;
    this.maxParcelasCartao = maxParcelasCartao;
    this.maxParcelasDebitoConta = maxParcelasDebitoConta;
    this.maxParcelasCartaoEspecial = maxParcelasCartaoEspecial;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeSeguradora() {
    return nomeSeguradora;
  }

  public void setNomeSeguradora(String nomeSeguradora) {
    this.nomeSeguradora = nomeSeguradora;
  }

  public LocalDate getDataCotacao() {
    return dataCotacao;
  }

  public void setDataCotacao(LocalDate dataCotacao) {
    this.dataCotacao = dataCotacao;
  }

  public Double getValorCotacao() {
    return valorCotacao;
  }

  public void setValorCotacao(Double valorCotacao) {
    this.valorCotacao = valorCotacao;
  }

  public byte[] getArquivoCotacao() {
    return arquivoCotacao;
  }

  public void setArquivoCotacao(byte[] arquivoCotacao) {
    this.arquivoCotacao = arquivoCotacao;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Carro getCarro() {
    return carro;
  }

  public void setCarro(Carro carro) {
    this.carro = carro;
  }

  public Integer getMaxParcelasPix() {
    return maxParcelasPix;
  }

  public void setMaxParcelasPix(Integer maxParcelasPix) {
    this.maxParcelasPix = maxParcelasPix;
  }

  public Integer getMaxParcelasBoleto() {
    return maxParcelasBoleto;
  }

  public void setMaxParcelasBoleto(Integer maxParcelasBoleto) {
    this.maxParcelasBoleto = maxParcelasBoleto;
  }

  public Integer getMaxParcelasCartao() {
    return maxParcelasCartao;
  }

  public void setMaxParcelasCartao(Integer maxParcelasCartao) {
    this.maxParcelasCartao = maxParcelasCartao;
  }

  public Integer getMaxParcelasDebitoConta() {
    return maxParcelasDebitoConta;
  }

  public void setMaxParcelasDebitoConta(Integer maxParcelasDebitoConta) {
    this.maxParcelasDebitoConta = maxParcelasDebitoConta;
  }

  public Integer getMaxParcelasCartaoEspecial() {
    return maxParcelasCartaoEspecial;
  }

  public void setMaxParcelasCartaoEspecial(Integer maxParcelasCartaoEspecial) {
    this.maxParcelasCartaoEspecial = maxParcelasCartaoEspecial;
  }

  @Override
  public String toString() {
    return "Cotacao [arquivoCotacao=" + arquivoCotacao + ", carro=" + carro + ", cliente=" + cliente + ", dataCotacao="
        + dataCotacao + ", id=" + id + ", maxParcelasBoleto=" + maxParcelasBoleto
        + ", maxParcelasCartao=" + maxParcelasCartao + ", maxParcelasCartaoEspecial=" + maxParcelasCartaoEspecial
        + ", maxParcelasDebitoConta=" + maxParcelasDebitoConta + ", maxParcelasPix=" + maxParcelasPix
        + ", nomeSeguradora=" + nomeSeguradora + ", valorCotacao=" + valorCotacao + "]";
  }
}
