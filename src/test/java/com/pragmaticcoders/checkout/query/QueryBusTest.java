package com.pragmaticcoders.checkout.query;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class QueryBusTest {

    private QueryBus bus;
    private Query query;

    @Before
    public void setUp() throws Exception {
        bus = new QueryBus();
        query = mock(Query.class);
    }

    @Test
    public void testExecute() throws Exception {
        QueryExecutor executor1 = mock(QueryExecutor.class);
        QueryExecutor executor2 = mock(QueryExecutor.class);
        QueryExecutor executor3 = mock(QueryExecutor.class);

        QueryExecutor[] executors = {executor1, executor2, executor3};

        bus.registerExecutors(executors);

        ResponseEntity responseEntity = mock(ResponseEntity.class);

        given(executor1.canExecute(query)).willReturn(false);
        given(executor2.canExecute(query)).willReturn(false);
        given(executor3.canExecute(query)).willReturn(true);
        given(executor3.execute(query, HttpStatus.ACCEPTED)).willReturn(responseEntity);

        ResponseEntity result = bus.execute(query, HttpStatus.ACCEPTED);

        assertEquals(result, responseEntity);
    }

    @Test(expected = Exception.class)
    public void testExecuteThrowException() throws Exception {
        QueryExecutor executor1 = mock(QueryExecutor.class);
        QueryExecutor executor2 = mock(QueryExecutor.class);

        QueryExecutor[] executors = {executor1, executor2};

        bus.registerExecutors(executors);

        given(executor1.canExecute(query)).willReturn(false);
        given(executor2.canExecute(query)).willReturn(false);

        bus.execute(query, HttpStatus.ACCEPTED);
    }
}