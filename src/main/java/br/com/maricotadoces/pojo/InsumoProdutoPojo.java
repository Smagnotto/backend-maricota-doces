package br.com.maricotadoces.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.domain.InsumoProduto;
import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoProdutoPojo {

    public InsumoProdutoPojo(InsumoProduto insumoProduto) {
        this.id = insumoProduto.getId();
        this.nome = insumoProduto.getInsumo().getNome();
        this.quantidade = insumoProduto.getQuantidade();
        this.tipo = insumoProduto.getTipo();
    }

    @ApiModelProperty(value = "Id Vinculo", readOnly = true)
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(value = "Nome do insumo", required = true, allowEmptyValue = false, dataType = "texto")
    private String nome;

    @ApiModelProperty(value = "Quantidade", required = true, allowEmptyValue = false, dataType = "java.math.BigDecimal")
    private Long quantidade;

    @ApiModelProperty(value = "Tipo", required = true, allowEmptyValue = false, dataType = "java.math.BigDecimal")
    private TipoInsumo tipo;
}
