package ru.itmo.kotiki.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.ampq.RabbitOwnerInfo;
import ru.itmo.kotiki.dto.MyUserDetails;
import ru.itmo.kotiki.entity.OperationType;
import ru.itmo.kotiki.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws JsonProcessingException {
        RabbitOwnerInfo info = new RabbitOwnerInfo(OperationType.GET_USER_BY_NAME, null, username, null);
        String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive("ownerQueue", objectMapper.writeValueAsString(info));
        RabbitOwnerInfo userResponseInfo = objectMapper.readValue(rabbitResponse, RabbitOwnerInfo.class);

        if (userResponseInfo == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(userResponseInfo);
    }
}
