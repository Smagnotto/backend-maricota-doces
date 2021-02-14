package br.com.maricotadoces.service;

import java.util.List;

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;

public interface InsumoService {
    
    List<InsumoPojo> getAll();
    InsumoPojo findById(long id);
    InsumoPojo create(CreateInsumoPojo alunoPojo);
    InsumoPojo update(Long id, CreateInsumoPojo insumoPojo);
    void delete(Long id);
}
