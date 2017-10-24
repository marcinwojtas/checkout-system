package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Promotion;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionMorphiaRepository extends BaseMorphiaRepository<Promotion> implements PromotionRepository {

    @Autowired
    public PromotionMorphiaRepository(Datastore datastore) {
        super(datastore);
    }
}
