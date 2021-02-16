package br.com.maricotadoces.service;

import java.util.List;

public interface GenericService<Pojo, CreatePojo> {
    
    List<Pojo> getAll();
    Pojo findById(long id);
    Pojo create(CreatePojo alunoPojo);
    Pojo update(Long id, CreatePojo insumoPojo);
    void delete(Long id);
}
