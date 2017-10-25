package com.pragmaticcoders.checkout.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.*;

@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    public enum Status {
        ORDERING, PAYMENT
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OrderItem {
        private Integer quantity;
        private Item item;
        private Integer totalCost;
    }

    @Id
    @Getter
    private UUID id;

    private List<OrderItem> items;

    @Getter
    private Integer price;

    private Set<Promotion> promotions = new HashSet<>();

    @Getter
    private Status status = Status.ORDERING;

    public Order(UUID id, List<OrderItem> items, Set<Promotion> promotions) {
        this.id = id;
        this.items = new ArrayList<>(items);
        this.promotions = new HashSet<>(promotions);

        calculate();
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<OrderItem> items) throws CannotChangeOrderException {
        if (status != Status.ORDERING) {
            throw new CannotChangeOrderException();
        }

        this.items = new ArrayList<>(items);
        calculate();
    }

    public void setPromotions(Set<Promotion> promotions) throws CannotChangeOrderException {
        if (status != Status.ORDERING) {
            throw new CannotChangeOrderException();
        }

        this.promotions = new HashSet<>(promotions);
        calculate();
    }

    public Set<Promotion> getPromotions() {
        return new HashSet<>(promotions);
    }

    private void calculate() {
        Integer price = 0;

        for (OrderItem item : items) {
            price += item.getTotalCost();
        }

        for (Promotion promotion : promotions) {
            price -= promotion.getDiscount();
        }

        this.price = price;
    }

    public void confirm() throws CannotChangeOrderException {
        if (status == Status.PAYMENT) {
            throw new CannotChangeOrderException();
        }

        calculate();
        status = Status.PAYMENT;
    }
}