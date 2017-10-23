package com.pragmaticcoders.checkout.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QueryRunner {
    private final QueryBus bus;

    @Autowired
    public QueryRunner(QueryBus bus) {
        this.bus = bus;
    }

    public ResponseEntity run(Query query, HttpStatus validHttpStatus) throws Exception {
        return bus.execute(query, validHttpStatus);
    }
}
