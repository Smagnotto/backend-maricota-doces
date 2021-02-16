package br.com.maricotadoces.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInsumoProdutoPojo {
    
    @ApiModelProperty(value = "Id do Insumo", required = true, allowEmptyValue = false)
    @JsonProperty("id_insumo")
    private Long idInsumo;

    @ApiModelProperty(value = "Quantidade do Insumo", required = true, allowEmptyValue = false )
    private Long quantidade;

    @ApiModelProperty(value = "Tipo do Insumo", required = true, allowEmptyValue = false )
    private TipoInsumo tipo;
}
