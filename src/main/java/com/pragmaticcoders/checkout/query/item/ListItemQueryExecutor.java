package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.dto.PriceDto;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListItemQueryExecutor implements QueryExecutor<ListItemQuery, List<ItemDto>> {

    private ItemRepository repository;

    @Autowired
    public ListItemQueryExecutor(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ItemDto>> execute(ListItemQuery query, HttpStatus validStatus) {
        List<Item> items = repository.findAll();

        List<ItemDto> itemDtos = items.stream()
            .map(item -> new ItemDto(
                item.getId(),
                item.getName(),
                item.getPriceList().stream()
                    .map(price -> new PriceDto(price.getQuantity(), price.getPrice()))
                    .collect(Collectors.toList())))
            .collect(Collectors.toList());

        return new ResponseEntity<List<ItemDto>>(itemDtos, validStatus);
    }
}
