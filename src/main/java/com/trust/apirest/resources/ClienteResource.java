package com.trust.apirest.resources;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trust.apirest.entities.Carro;
import com.trust.apirest.entities.Cliente;
import com.trust.apirest.entities.Cotacao;
import com.trust.apirest.entities.Endereco;
import com.trust.apirest.services.CarroService;
import com.trust.apirest.services.ClienteService;
import com.trust.apirest.services.CotacaoService;
import com.trust.apirest.services.EnderecoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/clientes")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @Autowired
  private CarroService carroService;

  @Autowired
  private EnderecoService enderecoService;

  @Autowired
  private CotacaoService cotacaoService;

  /*
   * 
   * GET Mappings
   * 
   */

  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    List<Cliente> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    Cliente obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping("/{clienteId}/carros")
  public ResponseEntity<List<Carro>> getCarrosByClienteId(@PathVariable Long clienteId) {
    List<Carro> carros = service.findCarrosByClienteId(clienteId);
    return ResponseEntity.ok().body(carros);
  }

  @GetMapping("/{clienteId}/carros/{carroId}")
  public ResponseEntity<Carro> getCarroByClienteIdAndCarroId(@PathVariable Long clienteId, @PathVariable Long carroId) {
    Carro carro = carroService.findCarroByClienteIdAndCarroId(clienteId, carroId);
    return ResponseEntity.ok().body(carro);
  }

  @GetMapping("/{clienteId}/carros/placa/{placa}")
  public ResponseEntity<Carro> getCarroByClienteIdAndCarroPlaca(@PathVariable Long clienteId,
      @PathVariable String placa) {
    Carro carro = carroService.findCarroByClienteIdAndCarroPlaca(clienteId, placa);
    return ResponseEntity.ok().body(carro);
  }

  @GetMapping("/{clienteId}/enderecos")
  public ResponseEntity<List<Endereco>> getEnderecosByClienteId(@PathVariable Long clienteId) {
    List<Endereco> enderecos = service.findEnderecosByClienteId(clienteId);
    return ResponseEntity.ok().body(enderecos);
  }

  @GetMapping("/{clienteId}/enderecos/{enderecoId}")
  public ResponseEntity<Endereco> getEnderecoByClienteIdAndEnderecoId(@PathVariable Long clienteId,
      @PathVariable Long enderecoId) {
    Endereco endereco = enderecoService.findEnderecoByClienteIdAndEnderecoId(clienteId, enderecoId);
    return ResponseEntity.ok().body(endereco);
  }

  @GetMapping("/{clienteId}/cotacoes")
  public ResponseEntity<List<Cotacao>> getCotacoesByClienteId(@PathVariable Long clienteId) {
    List<Cotacao> cotacoes = service.findCotacoesByClienteId(clienteId);
    return ResponseEntity.ok().body(cotacoes);
  }

  @GetMapping("/{clienteId}/cotacoes/{cotacaoId}")
  public ResponseEntity<Cotacao> getCotacaoByClienteIdAndCotacaoId(@PathVariable Long clienteId,
      @PathVariable Long cotacaoId) {
    Cotacao cotacao = cotacaoService.findCotacaoByClienteIdAndCotacaoId(clienteId, cotacaoId);
    return ResponseEntity.ok().body(cotacao);
  }

  @GetMapping("/{clienteId}/cotacoes/{cotacaoId}/download")
  public ResponseEntity<byte[]> downloadCotacao(@PathVariable Long clienteId, @PathVariable Long cotacaoId) {
    Cotacao cotacao = cotacaoService.findCotacaoByClienteIdAndCotacaoId(clienteId, cotacaoId);
    if (cotacao == null || cotacao.getArquivoCotacao() == null) {
      return ResponseEntity.notFound().build();
    }

    Cliente cliente = cotacao.getCliente();

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Cotacao_" + cliente.getNome() + "_"
        + cotacao.getNomeSeguradora() + "_" + cotacao.getDataCotacao() + ".pdf");

    // Assume que o arquivo é um PDF, ajuste conforme o tipo real do arquivo
    return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(cotacao.getArquivoCotacao());
  }

  @GetMapping(value = "/cpf/{cpf}")
  public ResponseEntity<List<Cliente>> findByCpfStartingWith(@PathVariable String cpf) {
    List<Cliente> list = service.findByCpfStartingWith(cpf);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/birthdays/current-month")
  public ResponseEntity<List<Cliente>> findCurrentMonthBirthdays() {
    List<Cliente> list = service.findCurrentMonthBirthdays();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/birthdays/month/{month}")
  public ResponseEntity<List<Cliente>> findClientesByMonthOfBirth(@PathVariable int month) {
    List<Cliente> list = service.findClientesByMonthOfBirth(month);
    return ResponseEntity.ok().body(list);
  }

  /*
   * 
   * POST Mappings
   * 
   */

  @PostMapping
  public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente obj) {
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(obj);
  }

  @PostMapping("/{clienteId}/carros")
  public ResponseEntity<Carro> insert(@PathVariable Long clienteId, @Valid @RequestBody Carro carro) {
    Carro savedCarro = carroService.insertWithCliente(clienteId, carro);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedCarro.getId()).toUri();
    return ResponseEntity.created(uri).body(savedCarro);
  }

  @PostMapping("/{clienteId}/enderecos")
  public ResponseEntity<Endereco> insert(@PathVariable Long clienteId, @Valid @RequestBody Endereco endereco) {
    Endereco savedEndereco = enderecoService.insertWithCliente(clienteId, endereco);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedEndereco.getId()).toUri();
    return ResponseEntity.created(uri).body(savedEndereco);
  }

  // @PostMapping("/{clienteId}/cotacoes")
  // public ResponseEntity<Cotacao> createCotacao(
  // @PathVariable Long clienteId,
  // @RequestParam("carroId") Long carroId,
  // @RequestParam("nomeSeguradora") String nomeSeguradora,
  // @RequestParam("dataCotacao") @DateTimeFormat(pattern = "dd/MM/yyyy")
  // LocalDate dataCotacao,
  // @RequestParam("valorCotacao") Double valorCotacao,
  // @RequestParam("arquivo") MultipartFile arquivo,
  // @RequestParam(value = "maxParcelasPix", required = false) Optional<Integer>
  // maxParcelasPix,
  // @RequestParam(value = "maxParcelasBoleto", required = false)
  // Optional<Integer> maxParcelasBoleto,
  // @RequestParam(value = "maxParcelasCartao", required = false)
  // Optional<Integer> maxParcelasCartao,
  // @RequestParam(value = "maxParcelasDebitoConta", required = false)
  // Optional<Integer> maxParcelasDebitoConta,
  // @RequestParam(value = "maxParcelasCartaoEspecial", required = false)
  // Optional<Integer> maxParcelasCartaoEspecial)
  // throws IOException {

  // Cotacao novaCotacao = cotacaoService.insertWithCliente(
  // clienteId,
  // carroId,
  // nomeSeguradora,
  // dataCotacao,
  // valorCotacao,
  // arquivo,
  // maxParcelasPix.orElse(null),
  // maxParcelasBoleto.orElse(null),
  // maxParcelasCartao.orElse(null),
  // maxParcelasDebitoConta.orElse(null),
  // maxParcelasCartaoEspecial.orElse(null));

  // URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
  // .buildAndExpand(novaCotacao.getId()).toUri();
  // return ResponseEntity.created(uri).body(novaCotacao);
  // }

  @PostMapping("/{clienteId}/cotacoes")
  public ResponseEntity<Cotacao> createCotacao(
      @PathVariable Long clienteId,
      @RequestParam("carroId") Long carroId,
      @RequestParam("nomeSeguradora") String nomeSeguradora,
      @RequestParam("dataCotacao") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataCotacao,
      @RequestParam("valorCotacao") Double valorCotacao,
      @RequestParam(value = "maxParcelasPix", required = false) Optional<Integer> maxParcelasPix,
      @RequestParam(value = "maxParcelasBoleto", required = false) Optional<Integer> maxParcelasBoleto,
      @RequestParam(value = "maxParcelasCartao", required = false) Optional<Integer> maxParcelasCartao,
      @RequestParam(value = "maxParcelasDebitoConta", required = false) Optional<Integer> maxParcelasDebitoConta,
      @RequestParam(value = "maxParcelasCartaoEspecial", required = false) Optional<Integer> maxParcelasCartaoEspecial)
      throws IOException {

    Cotacao novaCotacao = cotacaoService.insertWithCliente(
        clienteId,
        carroId,
        nomeSeguradora,
        dataCotacao,
        valorCotacao,
        maxParcelasPix.orElse(null),
        maxParcelasBoleto.orElse(null),
        maxParcelasCartao.orElse(null),
        maxParcelasDebitoConta.orElse(null),
        maxParcelasCartaoEspecial.orElse(null));

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novaCotacao.getId()).toUri();
    return ResponseEntity.created(uri).body(novaCotacao);
  }
  /*
   * 
   * DELETE Mappings
   * 
   */

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{clienteId}/carros/{carroId}")
  public ResponseEntity<Void> deleteCarro(@PathVariable Long clienteId, @PathVariable Long carroId) {
    carroService.delete(carroId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{clienteId}/enderecos/{enderecoId}")
  public ResponseEntity<Void> deleteEndereco(@PathVariable Long clienteId, @PathVariable Long enderecoId) {
    enderecoService.delete(enderecoId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{clienteId}/cotacoes/{cotacaoId}")
  public ResponseEntity<Void> deleteCotacao(@PathVariable Long clienteId, @PathVariable Long cotacaoId) {
    cotacaoService.delete(cotacaoId);
    return ResponseEntity.noContent().build();
  }

  /*
   * 
   * PUT Mappings
   * 
   */

  @PutMapping(value = "/{id}")
  public ResponseEntity<Cliente> update(@Valid @PathVariable Long id, @RequestBody Cliente obj) {
    obj = service.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{clienteId}/carros/{carroId}")
  public ResponseEntity<Carro> updateCarro(@PathVariable Long clienteId, @PathVariable Long carroId,
      @RequestBody Carro obj) {
    obj = carroService.update(carroId, obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{clienteId}/enderecos/{enderecoId}")
  public ResponseEntity<Endereco> updateEndereco(@PathVariable Long clienteId, @PathVariable Long enderecoId,
      @RequestBody Endereco obj) {
    obj = enderecoService.update(enderecoId, obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{clienteId}/cotacoes/{cotacaoId}")
  public ResponseEntity<Cotacao> updateCotacao(@PathVariable Long clienteId, @PathVariable Long cotacaoId,
      @RequestBody Cotacao obj) {
    obj = cotacaoService.update(cotacaoId, obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{clienteId}/cotacoes/{cotacaoId}/arquivo")
  public ResponseEntity<Cotacao> updateCotacaoArquivo(@PathVariable Long clienteId, @PathVariable Long cotacaoId,
      @RequestParam("arquivo") MultipartFile arquivo) throws IOException {
    Cotacao cotacao = cotacaoService.findCotacaoByClienteIdAndCotacaoId(clienteId, cotacaoId);
    cotacao.setArquivoCotacao(arquivo.getBytes());
    cotacao = cotacaoService.update(cotacaoId, cotacao);
    return ResponseEntity.ok().body(cotacao);
  }

}
