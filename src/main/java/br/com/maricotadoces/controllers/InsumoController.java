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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import br.com.maricotadoces.service.GenericService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * InsumoController
 */

@RestController
@RequestMapping("v1/insumos")
public class InsumoController {

    private final GenericService<InsumoPojo, CreateInsumoPojo> service;

    public InsumoController(GenericService<InsumoPojo, CreateInsumoPojo> service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma lista de insumos cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InsumoPojo> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o insumo cadastrado"),
            @ApiResponse(responseCode = "404", description = "Id do insumo informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InsumoPojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(responseCode = "200", description = "Retorna o insumo criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InsumoPojo create(@RequestBody @Valid CreateInsumoPojo insumoPojo) {
        return service.create(insumoPojo);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o insumo atualizado"),
            @ApiResponse(responseCode = "404", description = "Id do insumo informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InsumoPojo update(@PathVariable Long id, @RequestBody @Valid CreateInsumoPojo insumoPojo) {
        return service.update(id, insumoPojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}