package com.pragmaticcoders.checkout.query.promotion;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import com.pragmaticcoders.checkout.view.PromotionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SinglePromotionQueryExecutor implements QueryExecutor<SinglePromotionQuery, PromotionView> {

    private PromotionRepository repository;

    @Autowired
    public SinglePromotionQueryExecutor(PromotionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<PromotionView> execute(SinglePromotionQuery query, HttpStatus validStatus) {
        Promotion promotion = repository.findOne(query.getId());

        if (promotion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<UUID> uuids = promotion.getItems()
            .stream()
            .map(Item::getId)
            .collect(Collectors.toSet());

        PromotionView dto = new PromotionView(promotion.getId(), uuids, promotion.getDiscount());

        return new ResponseEntity<>(dto, validStatus);
    }
}
