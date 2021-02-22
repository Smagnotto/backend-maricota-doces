package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Endereco;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String logradouro;

    @ApiModelProperty(value = "CEP", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String cep;

    @ApiModelProperty(value = "Numero", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private Integer numero;

    @ApiModelProperty(value = "Complemento", dataType = "java.lang.String")
    private String complemento;
}
