package com.pragmaticcoders.checkout.command.item;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddItemCommandExecutorTest {

    private AddItemCommandExecutor executor;

    @Mock
    private ItemRepository repository;

    @Captor
    private ArgumentCaptor<Item> captor;

    @Before
    public void setUp() throws Exception {
        executor = new AddItemCommandExecutor(repository);
    }

    @Test
    public void testAddItem() {
        UUID uuid = UUID.randomUUID();

        ItemDto dto = new ItemDto(
            "foo",
            new ArrayList<ItemDto.Price>() {{
                add(new ItemDto.Price(1, BigDecimal.valueOf(1)));
                add(new ItemDto.Price(2, BigDecimal.valueOf(10)));
            }}
        );

        Item item = new Item(
            uuid,
            "foo",
            new ArrayList<Price>() {{
                add(new Price(BigDecimal.valueOf(1), 1));
                add(new Price(BigDecimal.valueOf(10), 2));
            }}
        );

        executor.execute(new AddItemCommand(uuid, dto));
        verify(repository).save(captor.capture());

        assertEquals(item, captor.getValue());
    }
}