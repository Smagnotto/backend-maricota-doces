package br.com.maricotadoces.controllers;

import java.util.List;

import javax.validation.Valid;

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
import br.com.maricotadoces.pojo.ProdutoPojo;
import br.com.maricotadoces.service.GenericService;
import br.com.maricotadoces.service.ListLikeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private final ListLikeService<ProdutoPojo, CreateProdutoPojo> service;

    public ProdutoController(ListLikeService<ProdutoPojo, CreateProdutoPojo> service) {
        this.service = service;
    }

    @ApiResponse(code = 200, message = "Retorna uma lista de produtos cadastrados")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProdutoPojo> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o produto cadastrado"),
            @ApiResponse(code = 404, message = "Id do produto informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProdutoPojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(code = 200, message = "Retorna uma lista de clientes cadastrados buscado pelo nome utilizando Like")
    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProdutoPojo> getByNome(@RequestParam(value = "nome") String nome) {
        return service.getAllLike(nome);
    }

    @ApiResponse(code = 200, message = "Retorna o produto criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoPojo create(@RequestBody @Valid CreateProdutoPojo ProdutoPojo) {
        return service.create(ProdutoPojo);
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o produto atualizado"),
            @ApiResponse(code = 404, message = "Id do produto informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProdutoPojo update(@PathVariable Long id, @RequestBody @Valid CreateProdutoPojo ProdutoPojo) {
        return service.update(id, ProdutoPojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
