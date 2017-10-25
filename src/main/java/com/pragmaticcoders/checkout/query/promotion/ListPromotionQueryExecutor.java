package com.pragmaticcoders.checkout.query.promotion;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.view.PromotionView;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListPromotionQueryExecutor implements QueryExecutor<ListPromotionQuery, List<PromotionView>> {

    private PromotionRepository repository;

    @Autowired
    public ListPromotionQueryExecutor(PromotionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<PromotionView>> execute(ListPromotionQuery query, HttpStatus validStatus) {
        List<Promotion> promotions = repository.findAll();
        List<PromotionView> promotionDtos = promotions.stream()
            .map(promotion -> new PromotionView(
                promotion.getId(),
                promotion.getItems().stream()
                    .map(Item::getId)
                    .collect(Collectors.toSet()),
                promotion.getDiscount()
            )).collect(Collectors.toList());

        return new ResponseEntity<>(promotionDtos, validStatus);
    }
}
