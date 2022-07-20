package ru.itmo.kotiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.ampq.RabbitOwnerInfo;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.OperationType;

import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OwnerController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll() throws JsonProcessingException {
        RabbitOwnerInfo info = new RabbitOwnerInfo(OperationType.GET_ALL, null, null, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("ownerQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, new TypeReference<>() {
        }));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OwnerDto> getById(@RequestParam("id") Integer id) throws JsonProcessingException {
        RabbitOwnerInfo info = new RabbitOwnerInfo(OperationType.GET_BY_ID, id, null, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("ownerQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, OwnerDto.class));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<OwnerDto>> getByName(String name) throws JsonProcessingException {
        RabbitOwnerInfo info = new RabbitOwnerInfo(OperationType.GET_BY_NAME, null, name, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("ownerQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, new TypeReference<>() {
        }));
    }
}
