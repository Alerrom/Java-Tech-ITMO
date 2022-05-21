package ru.itmo.kotiki.ampq;

import ru.itmo.kotiki.dto.AuthorizedUser;
import ru.itmo.kotiki.entity.OperationType;

public record RabbitOwnerInfo(
        OperationType operationType,
        Integer entityId,
        String name,
        AuthorizedUser user) {
}
