package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.view.ItemView;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListItemQueryExecutor implements QueryExecutor<ListItemQuery, List<ItemView>> {

    private ItemRepository repository;

    @Autowired
    public ListItemQueryExecutor(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ItemView>> execute(ListItemQuery query, HttpStatus validStatus) {
        List<Item> items = repository.findAll();

        List<ItemView> views = items.stream()
            .map(item -> new ItemView(
                item.getId(),
                item.getName(),
                item.getPrices().stream()
                    .map(price -> new ItemView.Price(price.getQuantity(), price.getPrice()))
                    .collect(Collectors.toList())))
            .collect(Collectors.toList());

        return new ResponseEntity<>(views, validStatus);
    }
}
