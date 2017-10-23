package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.query.Query;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class ItemMorphiaRepository implements ItemRepository {
    private Datastore datastore;

    @Autowired
    public ItemMorphiaRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void save(Item item) {
        datastore.save(item);
    }

    @Override
    public Item findOne(UUID uuid) {
        return datastore.get(Item.class, uuid);
    }

    @Override
    public List<Item> findAll() {
        return datastore.createQuery(Item.class).asList();
    }

    @Override
    public List<Item> find(Set<UUID> uuidSet) {
        return datastore.createQuery(Item.class).field("_id").in(uuidSet).asList();
    }
}
