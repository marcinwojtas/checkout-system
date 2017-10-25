package com.pragmaticcoders.checkout.command.promotion;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.dto.PromotionDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
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
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AddPromotionCommandExecutorTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private AddPromotionCommandExecutor executor;

    @Captor
    private ArgumentCaptor<Promotion> captor;

    @Test
    public void testAdd() {
        UUID promotionUuid = UUID.randomUUID();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        PromotionDto dto = new PromotionDto(
            new HashSet<UUID>() {{
                add(uuid1);
                add(uuid2);
            }},
            20
        );

        given(itemRepository.find(new HashSet<UUID>() {{
            add(uuid1);
            add(uuid2);
        }})).willReturn(
            new ArrayList<Item>() {{
                add(mock(Item.class));
                add(mock(Item.class));
            }}
        );

        AddPromotionCommand command = new AddPromotionCommand(promotionUuid, dto);
        executor.execute(command);
        verify(promotionRepository).save(captor.capture());
        Promotion promotion = captor.getValue();

        assertEquals(promotion.getId(), promotionUuid);
        assertEquals(2, promotion.getItems().size());
        assertEquals(Integer.valueOf(20), promotion.getDiscount());
    }


}