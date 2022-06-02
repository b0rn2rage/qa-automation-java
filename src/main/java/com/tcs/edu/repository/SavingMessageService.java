package com.tcs.edu.repository;

import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;

import java.util.Collection;
import java.util.UUID;

/**
 * Интерфейс, описывающий необходимые методы для сервиса сохранения сообщений
 */
public interface SavingMessageService {
    UUID log(Message message);

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();

    Collection<Message> findAllBySeverity(Severity level);
}
