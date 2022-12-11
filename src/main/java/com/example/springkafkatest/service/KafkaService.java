package com.example.springkafkatest.service;

import com.example.springkafkatest.messages.PersonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String, PersonDTO> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaService(KafkaTemplate<String, PersonDTO> kafkaStarshipTemplate, ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
    }

    public PersonDTO save(PersonDTO dto) {
        return null;
    }

    public void send(PersonDTO dto) {
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send("quickstart-events", dto);
    }

    @KafkaListener(groupId = "qsymond-group", topics = {"quickstart-events"}, containerFactory = "kafkaListenerContainerFactory")
    public void consume(PersonDTO dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
    }

    private String writeValueAsString(PersonDTO dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
