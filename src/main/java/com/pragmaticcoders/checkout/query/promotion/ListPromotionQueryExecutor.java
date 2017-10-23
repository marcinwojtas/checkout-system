package com.pragmaticcoders.checkout.query.promotion;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.dto.PromotionDto;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListPromotionQueryExecutor implements QueryExecutor<ListPromotionQuery, List<PromotionDto>> {

    private PromotionRepository repository;

    @Autowired
    public ListPromotionQueryExecutor(PromotionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<PromotionDto>> execute(ListPromotionQuery query, HttpStatus validStatus) {
        List<Promotion> promotions = repository.findAll();
        List<PromotionDto> promotionDtos = promotions.stream()
            .map(promotion -> new PromotionDto(
                promotion.getId(),
                promotion.getItems().stream()
                    .map(Item::getId)
                    .collect(Collectors.toSet()),
                promotion.getDiscount()
            )).collect(Collectors.toList());

        return new ResponseEntity<List<PromotionDto>>(promotionDtos, validStatus);
    }
}
