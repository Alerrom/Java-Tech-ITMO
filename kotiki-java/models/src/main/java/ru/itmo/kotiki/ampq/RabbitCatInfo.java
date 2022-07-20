package ru.itmo.kotiki.ampq;

import ru.itmo.kotiki.entity.OperationType;

public record RabbitCatInfo(
        OperationType operationType,
        Integer ownerId,
        Integer entityId,
        String name,
        String color
) {

}
