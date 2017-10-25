package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.command.CommandExecutor;
import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfirmOrderCommandExecutor implements CommandExecutor<ConfirmOrderCommand> {

    private final OrderRepository repository;

    @Autowired
    public ConfirmOrderCommandExecutor(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(ConfirmOrderCommand command) throws Exception {
        UUID uuid = command.getId();
        Order order = repository.findOne(uuid);
        order.confirm();

        repository.save(order);
    }
}
