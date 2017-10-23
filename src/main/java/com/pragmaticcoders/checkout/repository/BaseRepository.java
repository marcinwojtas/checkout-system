package com.pragmaticcoders.checkout.repository;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T> {
    void save(T item);
    T findOne(UUID uuid);
    List<T> findAll();
}
