package com.pragmaticcoders.checkout.command.promotion;

import com.pragmaticcoders.checkout.command.CommandExecutor;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.dto.PromotionDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AddPromotionCommandExecutor implements CommandExecutor<AddPromotionCommand> {

    private final PromotionRepository repository;
    private final ItemRepository itemRepository;

    @Autowired
    public AddPromotionCommandExecutor(PromotionRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void execute(AddPromotionCommand command) {
        UUID uuid = command.getUuid();
        PromotionDto dto = command.getDto();

        Set<Item> items = new HashSet<>(itemRepository.find(dto.getItems()));

        Promotion promotion = new Promotion(uuid, items, dto.getDiscount());

        repository.save(promotion);
    }
}
