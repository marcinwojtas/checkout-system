package com.pragmaticcoders.checkout.query;

import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface QueryExecutor<T extends Query, S> {
    ResponseEntity<S> execute(T query, HttpStatus validStatus);

    default boolean canExecute(T query) {
        Class executorType = GenericTypeResolver
            .resolveTypeArguments(getClass(), QueryExecutor.class)[0];

        return executorType == query.getClass();
    }
}
