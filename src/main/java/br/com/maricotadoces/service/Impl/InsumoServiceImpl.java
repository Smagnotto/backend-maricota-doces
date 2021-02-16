package br.com.maricotadoces.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.maricotadoces.domain.Insumo;
import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import br.com.maricotadoces.repository.InsumoRepository;
import br.com.maricotadoces.service.GenericService;

@Service
public class InsumoServiceImpl implements GenericService<InsumoPojo, CreateInsumoPojo> {

    private final InsumoRepository repository;

    public InsumoServiceImpl(InsumoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InsumoPojo> getAll() {
        List<Insumo> insumos = repository.findAll();

        return insumos.stream()
                .map(InsumoPojo::new)
                .collect(Collectors.toList());
    }

    @Override
    public InsumoPojo findById(long id) {
        Insumo insumo = getInsumoById(id);
        return new InsumoPojo(insumo);
    }


    @Override
    public InsumoPojo create(CreateInsumoPojo insumoPojo) {
        Insumo insumo = new Insumo(insumoPojo);
        Insumo insumoSaved = repository.save(insumo);
        return new InsumoPojo(insumoSaved);
    }

    @Override
    public InsumoPojo update(Long id, CreateInsumoPojo insumoPojo) {
        Insumo insumo = getInsumoById(id);

        insumo.setNome(insumoPojo.getNome());
        insumo.setAtivo(insumoPojo.getAtivo());

        Insumo updateInsumo = repository.save(insumo);
        return new InsumoPojo(updateInsumo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Insumo getInsumoById(long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
