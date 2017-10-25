package com.pragmaticcoders.checkout.query.order;

import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.domain.Promotion;
import com.pragmaticcoders.checkout.query.QueryExecutor;
import com.pragmaticcoders.checkout.repository.OrderRepository;
import com.pragmaticcoders.checkout.view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleOrderQueryExecutor implements QueryExecutor<SingleOrderQuery, OrderView> {

    private final OrderRepository orderRepository;

    @Autowired
    public SingleOrderQueryExecutor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<OrderView> execute(SingleOrderQuery query, HttpStatus validStatus) {
        Order order = orderRepository.findOne(query.getId());

        List<OrderView.Item> items = order.getItems()
            .stream()
            .map(item -> new OrderView.Item(
                item.getItem().getId(),
                item.getQuantity(),
                item.getItem().getName(),
                item.getTotalCost()
            ))
            .collect(Collectors.toList());

        Integer discount = 0;

        for (Promotion promotion : order.getPromotions()) {
            discount += promotion.getDiscount();
        }

        OrderView view = new OrderView(
            order.getId(),
            items,
            discount,
            order.getPrice(),
            order.getStatus().toString()
        );

        return new ResponseEntity<>(view, validStatus);
    }
}
