package org.flotares.tournaments.service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> getAll();
    Optional<T> getById(long id);
    T create(T t);
    T update(long id,T t);
    void delete(T t);
}
