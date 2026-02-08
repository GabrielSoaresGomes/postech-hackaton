package com.postech.hackaton.interface_adapter.data_sources.repositories;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<NE, E, ID> {
    Optional<E> findById(ID id);
    List<E> findAll(int size, long offset);
    E save(NE entity);
    Optional<E> update(E entity, Long id);
    Integer delete(ID id);
}