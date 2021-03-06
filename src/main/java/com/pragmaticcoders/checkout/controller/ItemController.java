package com.pragmaticcoders.checkout.controller;

import com.pragmaticcoders.checkout.command.CommandRunner;
import com.pragmaticcoders.checkout.command.item.AddItemCommand;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.query.QueryRunner;
import com.pragmaticcoders.checkout.query.item.ListItemQuery;
import com.pragmaticcoders.checkout.query.item.SingleItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ItemController extends BaseController {

    @Autowired
    public ItemController(CommandRunner commandRunner, QueryRunner queryRunner) {
        super(commandRunner, queryRunner);
    }

    @RequestMapping(path = "/item", method = POST)
    public ResponseEntity add(@RequestBody @Validated ItemDto dto) throws Exception {
        UUID uuid = UUID.randomUUID();
        commandRunner.run(new AddItemCommand(uuid, dto));

        return queryRunner.run(new SingleItemQuery(uuid), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/item/{id:" + UUID_REGEX + "}", method = GET)
    public ResponseEntity findOne(@PathVariable UUID id) throws Exception {
        return queryRunner.run(new SingleItemQuery(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/item", method = GET)
    public ResponseEntity findAll() throws Exception {
        return queryRunner.run(new ListItemQuery(), HttpStatus.OK);
    }
}