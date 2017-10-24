package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderMorphiaRepositoryTest extends RepositoryTestCase {
    private OrderMorphiaRepository repository;

    @Before
    public void setUp() {
        clearDb();
        repository = new OrderMorphiaRepository(datastore);
    }

    @Test
    public void testCreate() throws Exception {
        UUID uuid = UUID.randomUUID();
        repository.save(new Order(uuid, new HashMap<>(), new HashSet<>()));

        Order result = datastore.get(Order.class, uuid);

        assertEquals(uuid, result.getId());
    }

    @Test
    public void testGetOne() throws Exception {
        UUID uuid = UUID.randomUUID();
        datastore.save(new Order(uuid, new HashMap<>(), new HashSet<>()));

        Order result = repository.findOne(uuid);

        assertEquals(uuid, result.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        datastore.save(new Order(uuid1, new HashMap<>(), new HashSet<>()));

        UUID uuid2 = UUID.randomUUID();
        datastore.save(new Order(uuid2, new HashMap<>(), new HashSet<>()));

        List<Order> items = repository.findAll();

        assertEquals(2, items.size());
    }

    @Test
    public void testFindAllWithoutAny() {
        List<Order> items = repository.findAll();
        assertTrue(items.isEmpty());
    }
}
