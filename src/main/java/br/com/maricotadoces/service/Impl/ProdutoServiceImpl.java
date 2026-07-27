package br.com.maricotadoces.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.maricotadoces.domain.Insumo;
import br.com.maricotadoces.domain.Produto;
import br.com.maricotadoces.domain.ProdutoComponente;
import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.CreateInsumoProdutoPojo;
import br.com.maricotadoces.pojo.CreateProdutoComponentePojo;
import br.com.maricotadoces.pojo.CreateProdutoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import br.com.maricotadoces.pojo.PrecificacaoPojo;
import br.com.maricotadoces.pojo.ProdutoPojo;
import br.com.maricotadoces.repository.ProdutoRepository;
import br.com.maricotadoces.service.ConfiguracaoService;
import br.com.maricotadoces.service.GenericService;
import br.com.maricotadoces.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final GenericService<InsumoPojo, CreateInsumoPojo> insumoService;
    private final ConfiguracaoService configuracaoService;

    public ProdutoServiceImpl(ProdutoRepository repository,
            GenericService<InsumoPojo, CreateInsumoPojo> insumoService,
            ConfiguracaoService configuracaoService) {
        this.repository = repository;
        this.insumoService = insumoService;
        this.configuracaoService = configuracaoService;
    }

    @Override
    public List<ProdutoPojo> getAll() {
        List<Produto> produtos = repository.findAll();
        BigDecimal margem = margemAtual();

        return produtos.stream().map(p -> new ProdutoPojo(p, margem)).collect(Collectors.toList());
    }

    @Override
    public ProdutoPojo findById(long id) {
        Produto produto = getProdutoById(id);
        return new ProdutoPojo(produto, margemAtual());
    }

    @Override
    public ProdutoPojo create(CreateProdutoPojo produtoPojo) {
        Produto produto = new Produto(produtoPojo);

        montarInsumos(produto, produtoPojo.getInsumos());
        montarComponentes(produto, produtoPojo.getComponentes());

        BigDecimal margem = margemAtual();
        produto.setPreco(produto.calcularPreco(margem));

        Produto produtoSaved = repository.save(produto);
        return new ProdutoPojo(produtoSaved, margem);
    }

    @Override
    public ProdutoPojo update(Long id, CreateProdutoPojo produtoPojo) {
        Produto produto = getProdutoById(id);

        produto.setNome(produtoPojo.getNome());
        produto.setAtivo(produtoPojo.getAtivo());

        produto.getInsumos().clear();
        montarInsumos(produto, produtoPojo.getInsumos());

        produto.getComponentes().clear();
        montarComponentes(produto, produtoPojo.getComponentes());

        BigDecimal margem = margemAtual();
        produto.setPreco(produto.calcularPreco(margem));

        Produto updateProduto = repository.save(produto);
        return new ProdutoPojo(updateProduto, margem);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public PrecificacaoPojo simular(CreateProdutoPojo produtoPojo) {
        Produto produto = new Produto(produtoPojo);

        montarInsumos(produto, produtoPojo.getInsumos());
        montarComponentes(produto, produtoPojo.getComponentes());

        BigDecimal margem = margemAtual();
        return new PrecificacaoPojo(produto.calcularCusto(), produto.calcularPreco(margem));
    }

    private Produto getProdutoById(long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ProdutoPojo> getAllLike(String value) {
        List<Produto> produtos = repository.findByNomeLike("%" + value + "%");
        BigDecimal margem = margemAtual();

        return produtos.stream().filter(x -> x.getAtivo()).map(p -> new ProdutoPojo(p, margem))
                .collect(Collectors.toList());
    }

    private BigDecimal margemAtual() {
        return configuracaoService.get().getMargemPercentual();
    }

    private void montarInsumos(Produto produto, Set<CreateInsumoProdutoPojo> insumosPojo) {
        for (CreateInsumoProdutoPojo insumoPojo : insumosPojo) {
            Insumo insumo = new Insumo(insumoService.findById(insumoPojo.getIdInsumo()));

            produto.addInsumo(insumo, insumoPojo.getQuantidade(), insumoPojo.getTipo(), insumo.getPreco());
        }
    }

    private void montarComponentes(Produto produto, Set<CreateProdutoComponentePojo> componentesPojo) {
        for (CreateProdutoComponentePojo componentePojo : componentesPojo) {
            Produto produtoFilho = getProdutoById(componentePojo.getIdProduto());

            validarCiclo(produto, produtoFilho);

            produto.addComponente(produtoFilho, componentePojo.getQuantidade(), componentePojo.getTipo(),
                    produtoFilho.calcularCusto());
        }
    }

    private void validarCiclo(Produto produtoPai, Produto produtoFilho) {
        if (produtoPai.getId() == produtoFilho.getId() || contemProduto(produtoFilho, produtoPai.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Vínculo inválido: '" + produtoFilho.getNome() + "' depende de '" + produtoPai.getNome()
                            + "', isso criaria uma referência circular entre produtos");
        }
    }

    private boolean contemProduto(Produto produto, long idAlvo) {
        for (ProdutoComponente componente : produto.getComponentes()) {
            Produto filho = componente.getProdutoFilho();

            if (filho.getId() == idAlvo || contemProduto(filho, idAlvo)) {
                return true;
            }
        }

        return false;
    }
}
