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
    public static class Item {
        private Integer quantity;
        private com.pragmaticcoders.checkout.domain.Item item;
        private Integer cost;
    }

    @Id
    @Getter
    private UUID id;

    private List<Item> items;

    @Getter
    private Integer price;

    private Set<Promotion> promotions = new HashSet<>();

    @Getter
    private Status status = Status.ORDERING;

    public Order(UUID id, List<Order.Item> items, Set<Promotion> promotions) {
        this.id = id;
        this.items = items;
        this.promotions = promotions;

        calculate();
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<Order.Item> items) {
        this.items = new ArrayList<>(items);
        calculate();
    }

    public void setPromotions(Set<Promotion> promotions){
        this.promotions = new HashSet<>(promotions);
        calculate();
    }

    public Set<Promotion> getPromotions() {
        return Collections.unmodifiableSet(promotions);
    }

    public void calculate() {
        Integer price = 0;

        for (Order.Item item : items) {
            price += item.getCost();
        }

        for (Promotion promotion : promotions) {
            price -= promotion.getDiscount();
        }

        this.price = price;
    }

    public void confirm() {
        // todo validation if confirmed
        calculate();
        status = Status.PAYMENT;
    }
}