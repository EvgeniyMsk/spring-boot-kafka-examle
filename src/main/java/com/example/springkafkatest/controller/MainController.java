package com.example.springkafkatest.controller;

import com.example.springkafkatest.messages.PersonDTO;
import com.example.springkafkatest.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class MainController {

    private final KafkaService service;

    @Autowired
    public MainController(KafkaService service) {
        this.service = service;
    }

    @PostMapping
    private void post(@RequestBody PersonDTO personDTO) {
        service.send(personDTO);
    }



}