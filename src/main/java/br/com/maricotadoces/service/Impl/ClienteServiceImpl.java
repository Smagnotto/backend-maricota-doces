package br.com.maricotadoces.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.maricotadoces.domain.Cliente;
import br.com.maricotadoces.domain.Endereco;
import br.com.maricotadoces.pojo.ClientePojo;
import br.com.maricotadoces.pojo.CreateClientePojo;
import br.com.maricotadoces.repository.ClienteRepository;
import br.com.maricotadoces.service.ListLikeService;

@Service
public class ClienteServiceImpl implements ListLikeService<ClientePojo, CreateClientePojo> {

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
    public ClientePojo findById(long id) {
        Cliente cliente = getClienteById(id);
        return new ClientePojo(cliente);
    }

    @Override
    public ClientePojo create(CreateClientePojo clientePojo) {
        Cliente cliente = new Cliente(clientePojo);

        cliente.getEnderecos().add(new Endereco(clientePojo.getEndereco()));

        Cliente clienteSaved = repository.save(cliente);
        return new ClientePojo(clienteSaved);
    }

    @Override
    public ClientePojo update(Long id, CreateClientePojo ClientePojo) {
        Cliente cliente = getClienteById(id);

        // cliente.setNome(ClientePojo.getNome());
        // cliente.setAtivo(ClientePojo.getAtivo());
        // cliente.setPreco(ClientePojo.getPreco());

        // Cliente updateCliente = repository.save(cliente);
        // return new ClientePojo(updateCliente);
        return null;
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
