package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemQueryExecutor implements QueryExecutor<ItemQuery, ItemDto> {

    @Override
    public ResponseEntity<ItemDto> execute(ItemQuery query, HttpStatus validStatus) {
        return null;
    }
}
