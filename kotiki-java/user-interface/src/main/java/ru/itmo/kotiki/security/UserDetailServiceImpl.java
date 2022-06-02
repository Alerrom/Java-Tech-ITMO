package ru.itmo.kotiki.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.ampq.RabbitOwnerInfo;
import ru.itmo.kotiki.ampq.RabbitUserInfo;
import ru.itmo.kotiki.ampq.UserResponse;
import ru.itmo.kotiki.dto.MyUserDetails;
import ru.itmo.kotiki.entity.OperationType;

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
        RabbitUserInfo userInfo = objectMapper.readValue(rabbitResponse, RabbitUserInfo.class);

        if (userInfo == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(userInfo);
    }
}
