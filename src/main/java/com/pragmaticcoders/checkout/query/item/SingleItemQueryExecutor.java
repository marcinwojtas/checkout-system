package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.dto.PriceDto;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleItemQueryExecutor implements QueryExecutor<SingleItemQuery, ItemDto> {

    private ItemRepository repository;

    public SingleItemQueryExecutor(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ItemDto> execute(SingleItemQuery query, HttpStatus validStatus) {
        Item item = repository.findOne(query.getId());

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<PriceDto> prices = item.getPrices()
            .stream()
            .map(price -> new PriceDto(price.getQuantity(), price.getPrice()))
            .collect(Collectors.toList());

        ItemDto dto = new ItemDto(item.getId(), item.getName(), prices);

        return new ResponseEntity<>(dto, validStatus);
    }
}
