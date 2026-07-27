package br.com.maricotadoces.pojo;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.maricotadoces.domain.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientePojo {

    public ClientePojo(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.ativo = cliente.getAtivo();

        this.enderecos = cliente.getEnderecos().stream().map(EnderecoClientePojo::new).collect(Collectors.toSet());
    }

    @Schema(description = "Id", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Cliente ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;

    @Schema(description = "Endereco", accessMode = Schema.AccessMode.READ_ONLY)
    private Set<EnderecoClientePojo> enderecos;

}
