package br.com.maricotadoces.controllers;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maricotadoces.pojo.ConfiguracaoPojo;
import br.com.maricotadoces.pojo.UpdateConfiguracaoPojo;
import br.com.maricotadoces.service.ConfiguracaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("v1/configuracoes")
public class ConfiguracaoController {

    private final ConfiguracaoService service;

    public ConfiguracaoController(ConfiguracaoService service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "200", description = "Retorna as configurações do sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ConfiguracaoPojo get() {
        return service.get();
    }

    @ApiResponse(responseCode = "200", description = "Atualiza as configurações do sistema")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ConfiguracaoPojo update(@RequestBody @Valid UpdateConfiguracaoPojo pojo) {
        return service.update(pojo);
    }
}
