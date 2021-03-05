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

import br.com.maricotadoces.pojo.ClientePojo;
import br.com.maricotadoces.pojo.CreateClientePojo;
import br.com.maricotadoces.service.ListLikeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ListLikeService<ClientePojo, CreateClientePojo> service;

    public ClienteController(ListLikeService<ClientePojo, CreateClientePojo> service) {
        this.service = service;
    }

    @ApiResponse(code = 200, message = "Retorna uma lista de clientes cadastrados")
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ClientePojo> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cliente cadastrado"),
            @ApiResponse(code = 404, message = "Id do cliente informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientePojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(code = 200, message = "Retorna uma lista de clientes cadastrados buscado pelo nome utilizando Like")
    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ClientePojo> getByNome(@RequestParam(value = "nome") String nome) {
        return service.getAllLike(nome);
    }

    @ApiResponse(code = 200, message = "Retorna o cliente criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientePojo create(@RequestBody @Valid CreateClientePojo ClientePojo) {
        return service.create(ClientePojo);
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
            @ApiResponse(code = 404, message = "Id do cliente informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientePojo update(@PathVariable Long id, @RequestBody @Valid CreateClientePojo ClientePojo) {
        return service.update(id, ClientePojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}