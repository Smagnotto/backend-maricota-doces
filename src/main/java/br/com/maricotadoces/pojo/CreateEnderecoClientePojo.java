package br.com.maricotadoces.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEnderecoClientePojo {

    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String logradouro;

    @ApiModelProperty(value = "CEP", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String cep;

    @ApiModelProperty(value = "Numero", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private Integer numero;

    @ApiModelProperty(value = "Complemento", dataType = "java.lang.String")
    private String complemento;
}
