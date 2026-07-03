package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoClientePojo {

    public EnderecoClientePojo(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.numero = endereco.getNumero();
    }

    @Schema(description = "Id", accessMode = Schema.AccessMode.READ_ONLY)
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
