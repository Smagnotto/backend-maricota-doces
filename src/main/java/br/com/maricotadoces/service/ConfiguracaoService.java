package br.com.maricotadoces.service;

import br.com.maricotadoces.pojo.ConfiguracaoPojo;
import br.com.maricotadoces.pojo.UpdateConfiguracaoPojo;

public interface ConfiguracaoService {

    ConfiguracaoPojo get();
    ConfiguracaoPojo update(UpdateConfiguracaoPojo pojo);
}
