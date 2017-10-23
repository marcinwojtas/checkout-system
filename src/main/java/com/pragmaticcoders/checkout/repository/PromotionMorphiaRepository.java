package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Promotion;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PromotionMorphiaRepository implements PromotionRepository {

    private Datastore datastore;

    @Autowired
    public PromotionMorphiaRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void save(Promotion promotion) {
        datastore.save(promotion);
    }

    @Override
    public Promotion findOne(UUID uuid) {
        return datastore.get(Promotion.class, uuid);
    }

    @Override
    public List<Promotion> findAll() {
        return datastore.createQuery(Promotion.class).asList();
    }
}
