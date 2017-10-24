package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Item;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class ItemMorphiaRepository extends BaseMorphiaRepository<Item> implements ItemRepository {

    @Autowired
    public ItemMorphiaRepository(Datastore datastore) {
        super(datastore);
    }

    @Override
    public List<Item> find(Set<UUID> uuidSet) {
        return datastore.createQuery(Item.class).field("_id").in(uuidSet).asList();
    }
}
