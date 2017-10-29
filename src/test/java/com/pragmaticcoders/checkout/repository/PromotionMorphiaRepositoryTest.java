package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import com.pragmaticcoders.checkout.domain.Promotion;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class PromotionMorphiaRepositoryTest extends RepositoryTestCase {

    private PromotionMorphiaRepository repository;

    @Before
    public void setUp() {
        clearDb();
        repository = new PromotionMorphiaRepository(datastore);
    }

    @Test
    public void testCreate() throws Exception {
        UUID uuid = UUID.randomUUID();
        Set<Item> items = new HashSet<Item>() {{
            add(getItem());
            add(getItem());
        }};
        BigDecimal discount = BigDecimal.valueOf(40);

        repository.save(new Promotion(uuid, items, discount));

        Promotion result = datastore.get(Promotion.class, uuid);

        assertEquals(uuid, result.getId());
        assertEquals(items, result.getItems());
        assertEquals(discount, result.getDiscount());
    }

    @Test
    public void testGetOne() throws Exception {
        UUID uuid = UUID.randomUUID();
        Set<Item> items = new HashSet<Item>() {{
            add(getItem());
            add(getItem());
        }};
        BigDecimal discount = BigDecimal.valueOf(40);

        datastore.save(new Promotion(uuid, items, discount));

        Promotion result = repository.findOne(uuid);

        assertEquals(uuid, result.getId());
        assertEquals(items, result.getItems());
        assertEquals(discount, result.getDiscount());
    }

    @Test
    public void testFindAll() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        Set<Item> items1 = new HashSet<Item>() {{
            add(getItem());
            add(getItem());
        }};
        BigDecimal discount1 = BigDecimal.valueOf(40);

        Promotion promotion1 = new Promotion(uuid1, items1, discount1);

        datastore.save(promotion1);

        UUID uuid2 = UUID.randomUUID();
        Set<Item> items2 = new HashSet<Item>() {{
            add(getItem());
            add(getItem());
        }};
        BigDecimal discount2 = BigDecimal.valueOf(50);

        Promotion promotion2 = new Promotion(uuid2, items2, discount2);

        datastore.save(promotion2);

        List<Promotion> items = repository.findAll();

        assertEquals(2, items.size());
        assertThat(items, containsInAnyOrder(promotion1, promotion2));
    }

    @Test
    public void testFindAllWithoutAny() {
        List<Promotion> items = repository.findAll();
        assertTrue(items.isEmpty());
    }

    private Item getItem() {
        return new Item(
            UUID.randomUUID(),
            "foo",
            new ArrayList<Price>() {{
                add(new Price(BigDecimal.valueOf(1), 1));
            }}
        );
    }
}