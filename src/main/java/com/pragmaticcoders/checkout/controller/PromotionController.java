package com.pragmaticcoders.checkout.controller;

import com.pragmaticcoders.checkout.command.CommandRunner;
import com.pragmaticcoders.checkout.command.promotion.AddPromotionCommand;
import com.pragmaticcoders.checkout.dto.PromotionDto;
import com.pragmaticcoders.checkout.query.QueryRunner;
import com.pragmaticcoders.checkout.query.promotion.ListPromotionQuery;
import com.pragmaticcoders.checkout.query.promotion.SinglePromotionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.pragmaticcoders.checkout.controller.ItemController.UUID_REGEX;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PromotionController {
    private CommandRunner commandRunner;
    private QueryRunner queryRunner;

    @Autowired
    public PromotionController(CommandRunner commandRunner, QueryRunner queryRunner) {
        this.commandRunner = commandRunner;
        this.queryRunner = queryRunner;
    }

    @RequestMapping(path = "/promotion", method = POST)
    public ResponseEntity add(@RequestBody PromotionDto dto) throws Exception {
        UUID uuid = UUID.randomUUID();
        commandRunner.run(new AddPromotionCommand(uuid, dto));

        return queryRunner.run(new SinglePromotionQuery(uuid), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/promotion/{id:" + UUID_REGEX + "}", method = GET)
    public ResponseEntity findOne(@PathVariable UUID id) throws Exception {
        return queryRunner.run(new SinglePromotionQuery(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/promotion", method = GET)
    public ResponseEntity findAll() throws Exception {
        return queryRunner.run(new ListPromotionQuery(), HttpStatus.OK);
    }
}