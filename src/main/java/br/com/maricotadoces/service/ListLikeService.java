package br.com.maricotadoces.service;

import java.util.List;

public interface ListLikeService<Pojo, CreatePojo> extends GenericService<Pojo, CreatePojo> {
    
    List<Pojo> getAllLike(String value);
}
