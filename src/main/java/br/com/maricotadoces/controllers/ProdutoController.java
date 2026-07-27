package br.com.maricotadoces.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maricotadoces.pojo.CreateProdutoPojo;
import br.com.maricotadoces.pojo.PrecificacaoPojo;
import br.com.maricotadoces.pojo.ProdutoPojo;
import br.com.maricotadoces.service.ProdutoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma lista de produtos cadastrados")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdutoPojo> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Id do produto informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoPojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes cadastrados buscado pelo nome utilizando Like")
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdutoPojo> getByNome(@RequestParam(value = "nome") String nome) {
        return service.getAllLike(nome);
    }

    @ApiResponse(responseCode = "200", description = "Retorna o produto criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoPojo create(@RequestBody @Valid CreateProdutoPojo ProdutoPojo) {
        return service.create(ProdutoPojo);
    }

    @ApiResponse(responseCode = "200", description = "Simula o custo e o preço do produto (insumos + componentes) sem persistir")
    @PostMapping(path = "simular", produces = MediaType.APPLICATION_JSON_VALUE)
    public PrecificacaoPojo simular(@RequestBody @Valid CreateProdutoPojo produtoPojo) {
        return service.simular(produtoPojo);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Id do produto informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoPojo update(@PathVariable Long id, @RequestBody @Valid CreateProdutoPojo ProdutoPojo) {
        return service.update(id, ProdutoPojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
