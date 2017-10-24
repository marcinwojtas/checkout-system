package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Order;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMorphiaRepository extends BaseMorphiaRepository<Order> implements OrderRepository {

    @Autowired
    public OrderMorphiaRepository(Datastore datastore) {
        super(datastore);
    }
}
