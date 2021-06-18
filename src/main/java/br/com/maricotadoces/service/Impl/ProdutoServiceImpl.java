package br.com.maricotadoces.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.maricotadoces.domain.Insumo;
import br.com.maricotadoces.domain.Produto;
import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.CreateInsumoProdutoPojo;
import br.com.maricotadoces.pojo.CreateProdutoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import br.com.maricotadoces.pojo.ProdutoPojo;
import br.com.maricotadoces.repository.ProdutoRepository;
import br.com.maricotadoces.service.GenericService;
import br.com.maricotadoces.service.ListLikeService;

@Service
public class ProdutoServiceImpl implements ListLikeService<ProdutoPojo, CreateProdutoPojo> {

    private final ProdutoRepository repository;
    private final GenericService<InsumoPojo, CreateInsumoPojo> insumoService;

    public ProdutoServiceImpl(ProdutoRepository repository,
            GenericService<InsumoPojo, CreateInsumoPojo> insumoService) {
        this.repository = repository;
        this.insumoService = insumoService;
    }

    @Override
    public List<ProdutoPojo> getAll() {
        List<Produto> produtos = repository.findAll();

        return produtos.stream().map(ProdutoPojo::new).collect(Collectors.toList());
    }

    @Override
    public ProdutoPojo findById(long id) {
        Produto produto = getProdutoById(id);
        return new ProdutoPojo(produto);
    }

    @Override
    public ProdutoPojo create(CreateProdutoPojo produtoPojo) {
        Produto produto = new Produto(produtoPojo);

        for (CreateInsumoProdutoPojo insumoPojo : produtoPojo.getInsumos()) {
            Insumo insumo = new Insumo(insumoService.findById(insumoPojo.getIdInsumo()));

            produto.addInsumo(insumo, insumoPojo.getQuantidade(), insumoPojo.getTipo());
        }

        Produto produtoSaved = repository.save(produto);
        return new ProdutoPojo(produtoSaved);
    }

    @Override
    public ProdutoPojo update(Long id, CreateProdutoPojo produtoPojo) {
        Produto produto = getProdutoById(id);

        produto.setNome(produtoPojo.getNome());
        produto.setAtivo(produtoPojo.getAtivo());
        produto.setPreco(produtoPojo.getPreco());

        Produto updateProduto = repository.save(produto);
        return new ProdutoPojo(updateProduto);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    private Produto getProdutoById(long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ProdutoPojo> getAllLike(String value) {
        List<Produto> clientes = repository.findByNomeLike("%" + value + "%");

        return clientes.stream().filter(x -> x.getAtivo()).map(ProdutoPojo::new).collect(Collectors.toList());
    }
}
