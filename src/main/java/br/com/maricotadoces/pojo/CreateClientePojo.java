package br.com.maricotadoces.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientePojo {
    
    @Schema(description = "Nome", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Enderecos", requiredMode = Schema.RequiredMode.REQUIRED)
    private CreateEnderecoClientePojo endereco;
}
