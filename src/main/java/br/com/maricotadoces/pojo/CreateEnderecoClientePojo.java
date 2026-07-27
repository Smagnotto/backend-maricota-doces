package br.com.maricotadoces.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEnderecoClientePojo {

    @Schema(description = "Id do endereco existente. Informar apenas em atualizacoes, para manter a mesma referencia")
    private Long id;

    @Schema(description = "Nome", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logradouro;

    @Schema(description = "CEP", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cep;

    @Schema(description = "Numero", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numero;

    @Schema(description = "Complemento")
    private String complemento;
}
