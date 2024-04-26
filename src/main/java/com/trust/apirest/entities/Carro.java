package com.trust.apirest.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_carro")
public class Carro implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo marca é obrigatório")
  @Column(name = "marca")
  private String marca;

  @NotBlank(message = "Campo modelo é obrigatório")
  @Column(name = "modelo")
  private String modelo;

  @NotBlank(message = "Campo placa é obrigatório")
  @Column(name = "placa")
  private String placa;

  @NotBlank(message = "Campo ano do modelo é obrigatório")
  @Column(name = "ano_modelo")
  private String anoModelo;

  @Column(name = "cor")
  private String cor;

  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Cliente cliente;

  public Carro() {
  }

  public Carro(String marca, String modelo, String placa, String anoModelo, String cor) {
    this.marca = marca;
    this.modelo = modelo;
    this.placa = placa;
    this.anoModelo = anoModelo;
    this.cor = cor;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getAnoModelo() {
    return anoModelo;
  }

  public void setAnoModelo(String anoModelo) {
    this.anoModelo = anoModelo;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {
    return "Carro [anoModelo=" + anoModelo + ", cor=" + cor + ", id=" + id + ", marca=" + marca + ", modelo=" + modelo
        + ", placa=" + placa + "]";
  }

}
