package com.pragmaticcoders.checkout.query;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class QueryRunnerTest {
    private QueryBus bus;
    private Query query;
    private QueryRunner runner;

    @Before
    public void setUp() throws Exception {
        bus = mock(QueryBus.class);
        query = mock(Query.class);
        runner = new QueryRunner(bus);
    }

    @Test
    public void testRun() throws Exception {
        ResponseEntity responseEntity = mock(ResponseEntity.class);
        given(bus.execute(query, HttpStatus.ACCEPTED)).willReturn(responseEntity);

        ResponseEntity result = runner.run(query, HttpStatus.ACCEPTED);

        assertEquals(result, responseEntity);
    }
}