package br.com.maricotadoces.service;

import br.com.maricotadoces.pojo.CreateProdutoPojo;
import br.com.maricotadoces.pojo.PrecificacaoPojo;
import br.com.maricotadoces.pojo.ProdutoPojo;

public interface ProdutoService extends ListLikeService<ProdutoPojo, CreateProdutoPojo> {

    PrecificacaoPojo simular(CreateProdutoPojo produtoPojo);
}
