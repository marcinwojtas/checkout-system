package com.pragmaticcoders.checkout;


import com.pragmaticcoders.checkout.domain.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Item item = new Item("1", new HashMap<>());
        item.getName();
        SpringApplication.run(Application.class, args);
    }
}
