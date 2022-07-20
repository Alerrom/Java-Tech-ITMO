package ru.itmo.kotiki.web.ampq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.kotiki.ampq.RabbitOwnerInfo;
import ru.itmo.kotiki.ampq.RabbitUserInfo;
import ru.itmo.kotiki.data.OwnerDao;
import ru.itmo.kotiki.data.RoleDao;
import ru.itmo.kotiki.data.UserDao;
import ru.itmo.kotiki.dto.AuthorizedUser;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitOwnersConsumer {

    private final OwnerDao ownerDao;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ObjectMapper objectMapper;

    public RabbitOwnersConsumer(OwnerDao ownerDao, UserDao userDao, RoleDao roleDao, ObjectMapper objectMapper) {
        this.ownerDao = ownerDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "ownerQueue")
    @Transactional
    public String dispatch(String message) throws JsonProcessingException {
        RabbitOwnerInfo rabbitOwnerInfo = objectMapper.readValue(message, RabbitOwnerInfo.class);
        switch (rabbitOwnerInfo.operationType()) {
            case GET_ALL -> {
                return getAll();
            }
            case GET_BY_NAME -> {
                return getUserByName(rabbitOwnerInfo.name());
            }
            case GET_BY_ID -> {
                return getById(rabbitOwnerInfo.entityId());
            }
            case CREATE -> {
                return create(rabbitOwnerInfo.user());
            }
        }
        return "";
    }

    private String getById(Integer entityId) throws JsonProcessingException {
        Owner owner = ownerDao.getById(entityId);
        return objectMapper.writeValueAsString(new OwnerDto(owner));
    }

    private String create(AuthorizedUser user) throws JsonProcessingException {
        try {
            userDao.save(new User(user.getName(),
                    user.getPassword(),
                    user.isFlag(),
                    roleDao.findByRole(user.getRole()),
                    new Owner(user.getName(), user.getDate())));
        } catch (Exception e) {
            e.printStackTrace();
            return "user not created";
        }

        return objectMapper.writeValueAsString("user created");
    }

    private String getUserByName(String name) throws JsonProcessingException {
        User user = userDao.findByName(name);
        return objectMapper.writeValueAsString(
                new RabbitUserInfo(user.getName(),
                        user.getPassword(),
                        user.isFlag(),
                        user.getRole().getRole(),
                        user.getOwner().getId()));
    }

    private String getAll() throws JsonProcessingException {
        List<Owner> owners = ownerDao.findAll();
        List<OwnerDto> tmp = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            tmp.add(new OwnerDto(owners.get(i)));
        }
        return objectMapper.writeValueAsString(tmp.stream());
    }
}
