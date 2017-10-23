package com.pragmaticcoders.checkout.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QueryBus {
    private Set<QueryExecutor> executors;

    @Autowired
    public QueryBus(Set<QueryExecutor> executors) {
        this.executors = executors;
    }

    ResponseEntity execute(Query query, HttpStatus validResponseCode) throws Exception {
        for (QueryExecutor executor : executors) {
            if (executor.canExecute(query)) {
                return executor.execute(query, validResponseCode);
            }
        }

        throw new Exception("Handler for query: " + query.getClass() + " not found");
    }
}
