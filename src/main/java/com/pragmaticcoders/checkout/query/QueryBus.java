package com.pragmaticcoders.checkout.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QueryBus {
    private List<QueryExecutor> executors = new ArrayList<>();

    @Autowired
    public void registerExecutors(QueryExecutor[] executors) {
        this.executors.addAll(Arrays.asList(executors));
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
