package ru.itmo.kotiki.web.ampq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.ampq.RabbitCatInfo;
import ru.itmo.kotiki.data.CatDao;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.entity.Cat;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitCatsConsumer {
    private final CatDao catDao;
    private final ObjectMapper objectMapper;

    public RabbitCatsConsumer(CatDao catDao, ObjectMapper objectMapper) {
        this.catDao = catDao;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "catQueue")
    public String dispatch(String message) throws JsonProcessingException {
        RabbitCatInfo rabbitCatInfo = objectMapper.readValue(message, RabbitCatInfo.class);
        switch (rabbitCatInfo.operationType()) {
            case GET_ALL -> {
                return getAll(rabbitCatInfo.ownerId());
            }
            case GET_BY_ID -> {
                return getById(rabbitCatInfo.entityId(), rabbitCatInfo.ownerId());
            }
            case FILTER -> {
                return filterBy(rabbitCatInfo.color(), rabbitCatInfo.ownerId());
            }
        }
        return "";
    }

    private String filterBy(String color, int ownerId) throws JsonProcessingException {
        List<Cat> cats = catDao.findCatsByColorAndOwnerId(color, ownerId);
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return objectMapper.writeValueAsString(tmp.stream());
    }

    private String getById(int entityId, int ownerId) throws JsonProcessingException {
        Cat cat = catDao.findByIdAndOwnerId(entityId, ownerId);
        return objectMapper.writeValueAsString(new CatDto(cat));
    }

    private String getAll(int ownerId) throws JsonProcessingException {
        List<Cat> cats = catDao.findAllByOwnerId(ownerId);
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return objectMapper.writeValueAsString(tmp.stream());
    }
}
