package br.com.maricotadoces.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.maricotadoces.domain.Cliente;
import br.com.maricotadoces.domain.Endereco;
import br.com.maricotadoces.pojo.ClientePojo;
import br.com.maricotadoces.pojo.CreateClientePojo;
import br.com.maricotadoces.pojo.CreateEnderecoClientePojo;
import br.com.maricotadoces.repository.ClienteRepository;
import br.com.maricotadoces.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientePojo> getAll() {
        List<Cliente> clientes = repository.findAll();

        return clientes.stream().map(ClientePojo::new).collect(Collectors.toList());
    }

    @Override
    public List<ClientePojo> getAll(Boolean ativo) {
        List<Cliente> clientes = ativo == null ? repository.findAll() : repository.findByAtivo(ativo);

        return clientes.stream().map(ClientePojo::new).collect(Collectors.toList());
    }

    @Override
    public ClientePojo findById(long id) {
        Cliente cliente = getClienteById(id);
        return new ClientePojo(cliente);
    }

    @Override
    public ClientePojo create(CreateClientePojo clientePojo) {
        Cliente cliente = new Cliente(clientePojo);

        Cliente clienteSaved = repository.save(cliente);
        return new ClientePojo(clienteSaved);
    }

    @Override
    public ClientePojo update(Long id, CreateClientePojo clientePojo) {
        Cliente cliente = getClienteById(id);

        cliente.setNome(clientePojo.getNome());
        cliente.setAtivo(clientePojo.getAtivo());

        Map<Long, Endereco> enderecosExistentes = cliente.getEnderecos().stream()
                .collect(Collectors.toMap(Endereco::getId, e -> e));

        Set<Endereco> enderecosAtualizados = new HashSet<>();
        for (CreateEnderecoClientePojo enderecoPojo : clientePojo.getEnderecos()) {
            Endereco endereco = enderecoPojo.getId() != null ? enderecosExistentes.get(enderecoPojo.getId()) : null;

            if (endereco != null) {
                endereco.setLogradouro(enderecoPojo.getLogradouro());
                endereco.setNumero(enderecoPojo.getNumero());
                endereco.setComplemento(enderecoPojo.getComplemento());
                endereco.setCep(enderecoPojo.getCep());
            } else {
                endereco = new Endereco(enderecoPojo);
            }

            enderecosAtualizados.add(endereco);
        }

        cliente.getEnderecos().clear();
        cliente.getEnderecos().addAll(enderecosAtualizados);

        Cliente updateCliente = repository.save(cliente);
        return new ClientePojo(updateCliente);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    private Cliente getClienteById(long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ClientePojo> getAllLike(String value) {

        List<Cliente> clientes = repository.findByNomeLike("%" + value + "%");

        return clientes.stream().map(ClientePojo::new).collect(Collectors.toList());
    }
}
