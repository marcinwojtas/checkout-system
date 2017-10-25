package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.calulator.PriceCalculator;
import com.pragmaticcoders.checkout.command.CommandExecutor;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.dto.OrderDto;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import com.pragmaticcoders.checkout.repository.OrderRepository;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UpdateOrderCommandExecutor implements CommandExecutor<UpdateOrderCommand> {

    private ItemRepository itemRepository;
    private OrderRepository repository;
    private PromotionRepository promotionRepository;
    private PriceCalculator priceCalculator;

    @Autowired
    public UpdateOrderCommandExecutor(ItemRepository itemRepository, OrderRepository repository, PromotionRepository promotionRepository, PriceCalculator priceCalculator) {
        this.itemRepository = itemRepository;
        this.repository = repository;
        this.promotionRepository = promotionRepository;
        this.priceCalculator = priceCalculator;
    }

    @Override
    public void execute(UpdateOrderCommand command) throws Exception {
        UUID uuid = command.getId();
        OrderDto dto = command.getDto();

        Order order = repository.findOne(uuid);

        Set<UUID> uuids = dto.getItems()
            .stream()
            .map(item -> UUID.fromString(item.getId()))
            .collect(Collectors.toSet());

        List<Item> items = itemRepository.find(uuids);

        if (!ifAllItemsAreIn(items, uuids)) {
            throw new Exception();
        }

        List<Order.Item> orderItems = new ArrayList<>();
        for (Item item : items) {

            OrderDto.Item itemDto = dto.getItems()
                .stream()
                .filter(item1 -> item1.getId().equals(item.getId().toString()))
                .findFirst()
                .orElseThrow(Exception::new);

            orderItems.add(
                new Order.Item(
                    itemDto.getQuantity(),
                    item,
                    priceCalculator.calcCostOfItem(item, itemDto.getQuantity())
                )
            );
        }

        order.setItems(orderItems);
        order.setPromotions(getActivePromotions());

        repository.save(order);
    }

    private boolean ifAllItemsAreIn(Collection<Item> items, Collection<UUID> uuids) {
        return uuids.containsAll(
            items.stream()
                .map(Item::getId)
                .collect(Collectors.toList()));
    }

    private Set<Promotion> getActivePromotions() {
        return new HashSet<>(promotionRepository.findAll());
    }
}
