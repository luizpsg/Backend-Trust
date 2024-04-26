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
@Table(name = "tb_endereco")
public class Endereco implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo rua é obrigatório")
  @Column(name = "rua")
  private String rua;

  @NotBlank(message = "Campo numero é obrigatório")
  @Column(name = "numero")
  private String numero;

  @NotBlank(message = "Campo complemento é obrigatório")
  @Column(name = "complemento")
  private String complemento;

  @NotBlank(message = "Campo cep é obrigatório")
  @Column(name = "cep")
  private String cep;
 
  @NotBlank(message = "Campo bairro é obrigatório")
  @Column(name = "bairro")
  private String bairro;

  @NotBlank(message = "Campo cidade é obrigatório")
  @Column(name = "cidade")
  private String cidade;

  @NotBlank(message = "Campo estado é obrigatório")
  @Column(name = "estado")
  private String estado;

  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Cliente cliente;

  public Endereco() {
  }

  public Endereco(String rua, String numero, String complemento, String cep, String bairro, String cidade, String estado) {
    this.rua = rua;
    this.numero = numero;
    this.complemento = complemento;
    this.cep = cep;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {
    return "Endereco [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", cliente=" + cliente + ", complemento="
        + complemento + ", estado=" + estado + ", id=" + id + ", numero=" + numero + ", rua=" + rua + "]";
  }

}
