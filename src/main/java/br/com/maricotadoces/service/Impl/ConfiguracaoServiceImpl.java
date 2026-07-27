package br.com.maricotadoces.service.Impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.maricotadoces.domain.Configuracao;
import br.com.maricotadoces.pojo.ConfiguracaoPojo;
import br.com.maricotadoces.pojo.UpdateConfiguracaoPojo;
import br.com.maricotadoces.repository.ConfiguracaoRepository;
import br.com.maricotadoces.service.ConfiguracaoService;

@Service
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

    private static final long ID_CONFIGURACAO = 1L;

    private final ConfiguracaoRepository repository;

    public ConfiguracaoServiceImpl(ConfiguracaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfiguracaoPojo get() {
        return new ConfiguracaoPojo(getOuCriarConfiguracao());
    }

    @Override
    public ConfiguracaoPojo update(UpdateConfiguracaoPojo pojo) {
        Configuracao configuracao = getOuCriarConfiguracao();
        configuracao.setMargemPercentual(pojo.getMargemPercentual());

        return new ConfiguracaoPojo(repository.save(configuracao));
    }

    private Configuracao getOuCriarConfiguracao() {
        return repository.findById(ID_CONFIGURACAO).orElseGet(() -> {
            Configuracao configuracao = new Configuracao();
            configuracao.setId(ID_CONFIGURACAO);
            configuracao.setMargemPercentual(BigDecimal.ZERO);
            return repository.save(configuracao);
        });
    }
}
