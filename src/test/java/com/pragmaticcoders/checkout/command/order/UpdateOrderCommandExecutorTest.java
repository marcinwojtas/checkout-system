package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.calulator.PriceCalculator;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.dto.OrderDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import com.pragmaticcoders.checkout.repository.OrderRepository;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateOrderCommandExecutorTest {
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private PriceCalculator priceCalculator;

    @InjectMocks
    private UpdateOrderCommandExecutor executor;

    @Captor
    private ArgumentCaptor<Order> captor;

    @Test
    public void testUpdate() throws Exception {
        UUID uuid = UUID.randomUUID();
        UUID itemUuid1 = UUID.randomUUID();
        UUID itemUuid2 = UUID.randomUUID();

        Set<UUID> itemUuids = new HashSet<>();
        itemUuids.add(itemUuid1);
        itemUuids.add(itemUuid2);

        OrderDto.Item itemDto1 = mock(OrderDto.Item.class);
        OrderDto.Item itemDto2 = mock(OrderDto.Item.class);

        given(itemDto1.getId()).willReturn(itemUuid1.toString());
        given(itemDto1.getQuantity()).willReturn(1);
        given(itemDto2.getId()).willReturn(itemUuid2.toString());
        given(itemDto2.getQuantity()).willReturn(2);

        OrderDto dto = new OrderDto(
            new ArrayList<OrderDto.Item>() {{
                add(itemDto1);
                add(itemDto2);
            }}
        );

        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);

        given(item1.getId()).willReturn(itemUuid1);
        given(item2.getId()).willReturn(itemUuid2);

        Order order = new Order(
            uuid,
            new ArrayList<Order.Item>() {{
                add(mock(Order.Item.class));
            }},
            new HashSet<>()
        );

        given(orderRepository.findOne(uuid)).willReturn(order);

        UpdateOrderCommand command = new UpdateOrderCommand(uuid, dto);

        Promotion promotion = mock(Promotion.class);

        given(promotionRepository.findAll()).willReturn(
            new ArrayList<Promotion>() {{
                add(promotion);
            }}
        );

        given(itemRepository.find(itemUuids)).willReturn(
            new ArrayList<Item>() {{
                add(item1);
                add(item2);
            }}
        );

        given(priceCalculator.calcCostOfItem(item1, 1)).willReturn(10);
        given(priceCalculator.calcCostOfItem(item2, 2)).willReturn(13);


        executor.execute(command);
        verify(orderRepository).save(captor.capture());

        Order result = captor.getValue();

        assertEquals(uuid, result.getId());
        assertEquals(Order.Status.ORDERING, result.getStatus());
        assertEquals(result.getItems().size(), 2);
        assertTrue(result.getPromotions().contains(promotion));
        assertEquals(result.getPrice(), Integer.valueOf(23));
    }
}