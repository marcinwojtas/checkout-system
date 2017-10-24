package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class ItemMorphiaRepositoryTest extends RepositoryTestCase {
    private ItemMorphiaRepository repository;

    @Before
    public void setUp() {
        clearDb();
        repository = new ItemMorphiaRepository(datastore);
    }

    @Test
    public void testCreate() throws Exception {
        UUID uuid = UUID.randomUUID();
        String name = "foo";
        List<Price> prices = new ArrayList<Price>() {{
            add(new Price(1,10));
        }};

        repository.save(new Item(uuid, name, prices));

        Item result = datastore.get(Item.class, uuid);

        assertEquals(uuid, result.getId());
        assertEquals(name, result.getName());
        assertEquals(prices, result.getPrices());
    }

    @Test
    public void testGetOne() throws Exception {
        UUID uuid = UUID.randomUUID();
        String name = "foo";
        List<Price> prices = new ArrayList<Price>() {{
            add(new Price(1,10));
        }};

        datastore.save(new Item(uuid, name, prices));

        Item result = repository.findOne(uuid);

        assertEquals(uuid, result.getId());
        assertEquals(name, result.getName());
        assertEquals(prices, result.getPrices());
    }

    @Test
    public void testFindAll() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        String name1 = "foo";
        List<Price> prices1 = new ArrayList<Price>() {{
            add(new Price(100,1));
        }};
        Item item1 = new Item(uuid1, name1, prices1);
        datastore.save(item1);

        UUID uuid2 = UUID.randomUUID();
        String name2 = "bar";
        List<Price> prices2 = new ArrayList<Price>() {{
            add(new Price(200,2));
        }};
        Item item2 = new Item(uuid2, name2, prices2);
        datastore.save(item2);

        List<Item> items = repository.findAll();

        assertEquals(2, items.size());
        assertThat(items, containsInAnyOrder(item1, item2));
    }

    @Test
    public void testFindAllWithoutAny() {
        List<Item> items = repository.findAll();
        assertTrue(items.isEmpty());
    }

    @Test
    public void testFindByUUIDs() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        String name1 = "foo";
        List<Price> prices1 = new ArrayList<Price>() {{
            add(new Price(100,1));
        }};
        Item item1 = new Item(uuid1, name1, prices1);
        datastore.save(item1);

        UUID uuid2 = UUID.randomUUID();
        String name2 = "bar";
        List<Price> prices2 = new ArrayList<Price>() {{
            add(new Price(200,2));
        }};
        Item item2 = new Item(uuid2, name2, prices2);
        datastore.save(item2);

        datastore.save(new Item(UUID.randomUUID(), name2, prices2));

        List<Item> items = repository.find(
            new HashSet<UUID>() {{
                add(uuid1);
                add(uuid2);
            }}
        );

        assertEquals(2, items.size());
        assertThat(items, containsInAnyOrder(item1, item2));
    }
}