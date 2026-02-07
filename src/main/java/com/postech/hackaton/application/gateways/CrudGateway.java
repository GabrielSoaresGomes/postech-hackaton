package com.postech.hackaton.application.gateways;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface CrudGateway<E, ID> {
    E findById(ID id);
    List<E> findAll(Pageable pageable);
    E save(E entity);
    E update(E entity, ID id);
    void delete(ID id);
}