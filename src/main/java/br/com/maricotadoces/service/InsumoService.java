package br.com.maricotadoces.service;

import java.util.List;

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;

public interface InsumoService extends GenericService<InsumoPojo, CreateInsumoPojo> {

    List<InsumoPojo> getAll(Boolean ativo);
}
