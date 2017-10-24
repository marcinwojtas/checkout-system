package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.command.CommandExecutor;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.dto.OrderDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import com.pragmaticcoders.checkout.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddOrderCommandExecutor implements CommandExecutor<AddOrderCommand> {

    private OrderRepository repository;
    private ItemRepository itemRepository;

    @Autowired
    public AddOrderCommandExecutor(OrderRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void execute(AddOrderCommand command) {
        UUID uuid = command.getId();
        OrderDto dto = command.getDto();

        // todo add item validation

        Set<UUID> uuids = dto.getItems()
            .stream()
            .map(item -> UUID.fromString(item.getId()))
            .collect(Collectors.toSet());

        List<Item> items = itemRepository.find(uuids);

        Map<Integer, Item> itemsWithQuantity = new HashMap<>();
        for (Item item : items) {
            itemsWithQuantity.put(
                dto.getItems()
                    .stream()
                    .filter(item1 -> item1.getId().equals(item.getId().toString()))
                    .findFirst()
                    .get()
                    .getQuantity(),
                item
            );
        }

        // todo add promotions

        Order order = new Order(uuid, itemsWithQuantity, new HashSet<>());
        repository.save(order);
    }
}
