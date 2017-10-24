package com.pragmaticcoders.checkout.repository;

import org.mongodb.morphia.Datastore;
import org.springframework.core.GenericTypeResolver;

import java.util.List;
import java.util.UUID;

public class BaseMorphiaRepository<T> implements BaseRepository<T> {

    protected final Datastore datastore;

    BaseMorphiaRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void save(T object) {
        datastore.save(object);
    }

    @Override
    public T findOne(UUID uuid) {
        return datastore.get(getThisObjectClass(), uuid);
    }

    @Override
    public List<T> findAll() {
        return datastore.find(getThisObjectClass()).asList();
    }

    private Class<T> getThisObjectClass() {
        return (Class<T>) GenericTypeResolver
            .resolveTypeArgument(getClass(), BaseMorphiaRepository.class);
    }
}
