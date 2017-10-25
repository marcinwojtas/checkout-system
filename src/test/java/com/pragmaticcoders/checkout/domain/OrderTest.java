package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class OrderTest {

    @Test
    public void createAndModifyTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.getItems().add(mock(Order.OrderItem.class));
        order.getPromotions().add(mock(Promotion.class));

        assertEquals(uuid, order.getId());
        assertEquals(1, order.getItems().size());
        assertEquals(1, order.getPromotions().size());
        assertEquals(Integer.valueOf(2), order.getPrice());
        assertEquals(Order.Status.ORDERING, order.getStatus());
    }

    @Test
    public void settingItemsAndPromotionTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.setItems(
            new ArrayList<Order.OrderItem>() {{
                add(new Order.OrderItem(
                    1,
                    mock(Item.class),
                    100
                ));
            }}
        );

        order.setPromotions(
            new HashSet<Promotion>() {{
                add(
                    new Promotion(
                        UUID.randomUUID(),
                        new HashSet<Item>() {{
                            add(mock(Item.class));
                        }},
                        1
                    )
                );
            }}
        );


        assertEquals(uuid, order.getId());
        assertEquals(1, order.getItems().size());
        assertEquals(1, order.getPromotions().size());
        assertEquals(Integer.valueOf(99), order.getPrice());
        assertEquals(Order.Status.ORDERING, order.getStatus());
    }

    @Test
    public void confirmOrderTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.confirm();

        assertEquals(uuid, order.getId());
        assertEquals(1, order.getItems().size());
        assertEquals(1, order.getPromotions().size());
        assertEquals(Integer.valueOf(2), order.getPrice());
        assertEquals(Order.Status.PAYMENT, order.getStatus());
    }

    @Test(expected = CannotChangeOrderException.class)
    public void confirmThrowExceptionTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.confirm();
        order.confirm();
    }

    @Test(expected = CannotChangeOrderException.class)
    public void setPromotionOnConfirmedThrowExceptionTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.confirm();

        order.setPromotions(new HashSet<>());
    }

    @Test(expected = CannotChangeOrderException.class)
    public void setItemsOnConfirmedThrowExceptionTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = createOrder(uuid);

        order.confirm();

        order.setItems(new ArrayList<>());
    }

    private Order createOrder(UUID uuid) {
        return new Order(
            uuid,
            new ArrayList<Order.OrderItem>() {{
                add(new Order.OrderItem(
                    1,
                    mock(Item.class),
                    2
                ));
            }},
            new HashSet<Promotion>() {{
                add(mock(Promotion.class));
            }}
        );
    }
}