package com.trust.apirest.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank(message = "Campo nome é obrigatório")
  @Column(name = "nome")
  private String nome;

  @NotBlank(message = "Campo cpf é obrigatório")
  @Size(min = 11, max = 11, message = "O campo cpf deve ter 11 caracteres")
  @Column(name = "cpf")
  private String cpf;

  @NotBlank(message = "Campo telefone é obrigatório")
  @Size(max = 14, message = "O campo telefone deve ter no máximo 13 caracteres")
  @Column(name = "telefone")
  private String telefone;

  @NotNull(message = "Campo data de nascimento é obrigatório")
  @Column(name = "DataNascimento")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;

  @NotBlank(message = "Campo email é obrigatório")
  @Email(message = "Favor inserir um email válido")
  @Column(name = "email")
  private String email;

  @Size(min = 6, max = 30, message = "A senha deve ter no mínimo 6 caracteres")
  @Column(name = "senha")
  private String senha;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Carro> carros = new ArrayList<>();

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Endereco> enderecos = new ArrayList<>();

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Cotacao> cotacoes = new ArrayList<>();

  public Cliente() {
  }

  public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, String email, String senha) {
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
    this.email = email;
    this.senha = senha;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Carro> getCarros() {
    return carros;
  }

  public void setCarros(List<Carro> carros) {
    this.carros = carros;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public List<Cotacao> getCotacoes() {
    return cotacoes;
  }

  public void setCotacoes(List<Cotacao> cotacoes) {
    this.cotacoes = cotacoes;
  }

  @Override
  public String toString() {
    return "Cliente [cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", email=" + email + ", id=" + id + ", nome="
        + nome + ", senha=" + senha + ", telefone=" + telefone + "]";
  }
}
