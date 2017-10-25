package com.pragmaticcoders.checkout.controller;

import com.pragmaticcoders.checkout.command.CommandRunner;
import com.pragmaticcoders.checkout.command.order.AddOrderCommand;
import com.pragmaticcoders.checkout.command.order.ConfirmOrderCommand;
import com.pragmaticcoders.checkout.command.order.UpdateOrderCommand;
import com.pragmaticcoders.checkout.dto.OrderDto;
import com.pragmaticcoders.checkout.query.QueryRunner;
import com.pragmaticcoders.checkout.query.order.SingleOrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.pragmaticcoders.checkout.controller.ItemController.UUID_REGEX;

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

    @RequestMapping(value = "/order/{id:" + UUID_REGEX + "}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable UUID id) throws Exception {
        return queryRunner.run(new SingleOrderQuery(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id:" + UUID_REGEX + "}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody OrderDto dto, @PathVariable UUID id) throws Exception {
        commandRunner.run(new UpdateOrderCommand(id, dto));

        return queryRunner.run(new SingleOrderQuery(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id:" + UUID_REGEX + "}/confirm", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable UUID id) throws Exception {
        commandRunner.run(new ConfirmOrderCommand(id));

        return queryRunner.run(new SingleOrderQuery(id), HttpStatus.OK);
    }
}