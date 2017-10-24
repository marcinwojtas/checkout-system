package com.pragmaticcoders.checkout.command.item;

import com.pragmaticcoders.checkout.command.CommandExecutor;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddItemCommandExecutor implements CommandExecutor<AddItemCommand> {

    private ItemRepository repository;

    @Autowired
    public AddItemCommandExecutor(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(AddItemCommand command) {
        ItemDto dto = command.getItemDto();
        UUID uuid = command.getUuid();

        List<Price> prices = dto.getPrices()
            .stream()
            .map(p -> new Price(p.getPrice(), p.getQuantity()))
            .collect(Collectors.toList());

        Item item = new Item(uuid, dto.getName(), prices);

        repository.save(item);
    }
}
