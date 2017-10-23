package com.pragmaticcoders.checkout.controller;

import com.pragmaticcoders.checkout.command.item.AddItemCommand;
import com.pragmaticcoders.checkout.command.CommandRunner;
import com.pragmaticcoders.checkout.dto.ItemDto;
import com.pragmaticcoders.checkout.query.QueryRunner;
import com.pragmaticcoders.checkout.query.item.ItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ItemController {

    private CommandRunner commandRunner;
    private QueryRunner queryRunner;

    @Autowired
    public ItemController(CommandRunner commandRunner, QueryRunner queryRunner) {
        this.commandRunner = commandRunner;
        this.queryRunner = queryRunner;
    }

    @RequestMapping(path = "/item", method = POST)
    public ResponseEntity addItem(@RequestParam ItemDto dto) throws Exception {
        UUID uuid = UUID.randomUUID();
        commandRunner.run(new AddItemCommand(uuid, dto));

        return queryRunner.run(new ItemQuery(uuid), HttpStatus.CREATED);
    }
}
