package ru.itmo.kotiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.kotiki.ampq.RabbitOwnerInfo;
import ru.itmo.kotiki.ampq.UserResponse;
import ru.itmo.kotiki.dto.AuthorizedUser;
import ru.itmo.kotiki.entity.OperationType;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    ;

    public UserController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getByName(@RequestBody AuthorizedUser user) throws JsonProcessingException {
        RabbitOwnerInfo info = new RabbitOwnerInfo(OperationType.CREATE, null, null, user);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("ownerQueue", objectMapper.writeValueAsString(info));
        return ResponseEntity.ok(objectMapper.readValue(rabbitResponse, UserResponse.class));
    }
}
