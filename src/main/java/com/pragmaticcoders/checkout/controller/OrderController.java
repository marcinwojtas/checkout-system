package com.pragmaticcoders.checkout.controller;

import com.pragmaticcoders.checkout.command.CommandRunner;
import com.pragmaticcoders.checkout.command.order.AddOrderCommand;
import com.pragmaticcoders.checkout.dto.OrderDto;
import com.pragmaticcoders.checkout.query.QueryRunner;
import com.pragmaticcoders.checkout.query.order.SingleOrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

    private CommandRunner commandRunner;
    private QueryRunner queryRunner;

    @Autowired
    public OrderController(CommandRunner commandRunner, QueryRunner queryRunner) {
        this.commandRunner = commandRunner;
        this.queryRunner = queryRunner;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity addOrder(@RequestBody OrderDto dto) throws Exception {
        UUID uuid = UUID.randomUUID();
        commandRunner.run(new AddOrderCommand(uuid, dto));

        return queryRunner.run(new SingleOrderQuery(uuid), HttpStatus.CREATED);
    }
}
