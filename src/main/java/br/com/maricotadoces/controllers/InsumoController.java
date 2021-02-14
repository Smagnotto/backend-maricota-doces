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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import br.com.maricotadoces.service.InsumoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * InsumoController
 */

@RestController
@RequestMapping("insumos")
public class InsumoController {

    private final InsumoService service;

    public InsumoController(InsumoService service) {
        this.service = service;
    }

    @ApiResponse(code = 200, message = "Retorna uma lista de alunos cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<InsumoPojo> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o aluno cadastrado"),
            @ApiResponse(code = 404, message = "Id do aluno informado não existe") })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InsumoPojo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiResponse(code = 200, message = "Retorna o aluno criado")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InsumoPojo create(@RequestBody @Valid CreateInsumoPojo alunoPojo) {
        return service.create(alunoPojo);
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o aluno atualizado"),
            @ApiResponse(code = 404, message = "Id do aluno informado não existe") })
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InsumoPojo update(@PathVariable Long id, @RequestBody @Valid CreateInsumoPojo alunoPojo) {
        return service.update(id, alunoPojo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}