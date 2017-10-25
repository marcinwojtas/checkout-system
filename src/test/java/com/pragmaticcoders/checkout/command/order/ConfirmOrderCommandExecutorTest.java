package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.repository.OrderRepository;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmOrderCommandExecutorTest {

    @Mock
    private OrderRepository repository;

    @Captor
    private ArgumentCaptor<Order> captor;

    @InjectMocks
    private ConfirmOrderCommandExecutor executor;

    @Test
    public void confirmOrderTest() {
        UUID uuid = UUID.randomUUID();
        ConfirmOrderCommand command = new ConfirmOrderCommand(uuid);

        given(repository.findOne(uuid)).willReturn(
            new Order(
                uuid,
                new ArrayList<>(),
                new HashSet<>()
            )
        );

        executor.execute(command);

        verify(repository).save(captor.capture());

        assertEquals(Order.Status.PAYMENT, captor.getValue().getStatus());
    }
}