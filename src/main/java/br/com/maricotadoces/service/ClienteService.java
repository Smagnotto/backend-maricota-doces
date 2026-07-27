package br.com.maricotadoces.service;

import java.util.List;

import br.com.maricotadoces.pojo.ClientePojo;
import br.com.maricotadoces.pojo.CreateClientePojo;

public interface ClienteService extends ListLikeService<ClientePojo, CreateClientePojo> {

    List<ClientePojo> getAll(Boolean ativo);
}
