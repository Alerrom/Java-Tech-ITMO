package ru.itmo.kotiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.ampq.RabbitCatInfo;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.dto.MyUserDetails;
import ru.itmo.kotiki.entity.OperationType;

import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CatController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getAll() throws JsonProcessingException {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        RabbitCatInfo info = new RabbitCatInfo(OperationType.GET_ALL, ownerId, null, null, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("catQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, new TypeReference<>() {}));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDto> getById(@RequestParam("id") Integer id) throws JsonProcessingException {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        RabbitCatInfo info = new RabbitCatInfo(OperationType.GET_BY_ID, ownerId, id, null, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("catQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, CatDto.class));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<CatDto>> getByColor(@RequestParam("color") String color) throws JsonProcessingException {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        RabbitCatInfo info = new RabbitCatInfo(OperationType.FILTER, ownerId, null, null, color);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("catQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, new TypeReference<>() {}));
    }
}
