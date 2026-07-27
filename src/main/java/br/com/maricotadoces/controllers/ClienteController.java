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

import br.com.maricotadoces.pojo.ClientePojo;
import br.com.maricotadoces.pojo.CreateClientePojo;
import br.com.maricotadoces.service.ClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes cadastrados, podendo ser filtrada pelo campo ativo")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientePojo> getAll(@RequestParam(value = "ativo", required = false) Boolean ativo) {
        return service.getAll(ativo);
    }


    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes cadastrados buscado pelo nome utilizando Like")
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientePojo> getByNome(@RequestParam(value = "nome") String nome) {
        return service.getAllLike(nome);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o Cliente cadastrado"),
            @ApiResponse(responseCode = "404", description = "Id do Cliente informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientePojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(responseCode = "200", description = "Retorna o Cliente criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientePojo create(@RequestBody @Valid CreateClientePojo ClientePojo) {
        return service.create(ClientePojo);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o Cliente atualizado"),
            @ApiResponse(responseCode = "404", description = "Id do Cliente informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientePojo update(@PathVariable Long id, @RequestBody @Valid CreateClientePojo ClientePojo) {
        return service.update(id, ClientePojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}