package com.pragmaticcoders.checkout.repository;

import com.pragmaticcoders.checkout.domain.Item;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ItemRepository extends BaseRepository<Item> {
    List<Item> find(Set<UUID> uuidSet);
}
