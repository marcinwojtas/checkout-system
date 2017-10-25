package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.ItemRepository;
import com.pragmaticcoders.checkout.view.ItemView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleItemQueryExecutor implements QueryExecutor<SingleItemQuery, ItemView> {

    private ItemRepository repository;

    public SingleItemQueryExecutor(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ItemView> execute(SingleItemQuery query, HttpStatus validStatus) {
        Item item = repository.findOne(query.getId());

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ItemView.Price> prices = item.getPrices()
            .stream()
            .map(price -> new ItemView.Price(price.getQuantity(), price.getPrice()))
            .collect(Collectors.toList());

        ItemView view = new ItemView(item.getId(), item.getName(), prices);

        return new ResponseEntity<>(view, validStatus);
    }
}
