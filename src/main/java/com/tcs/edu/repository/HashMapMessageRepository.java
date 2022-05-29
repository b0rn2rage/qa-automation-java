package com.tcs.edu.repository;

import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс c CRUD методами для сообщений
 */
public class HashMapMessageRepository implements MessageRepository{
    private final Map<UUID, Message> messages = new HashMap<>();

    /**
     * Создать пару [ключ: значение]
     * @param message - сообщение для хеширования
     * @return ключ хеша
     */
    @Override
    public UUID create(Message message) {
        UUID id = UUID.randomUUID();
        messages.put(id, message);
        message.setId(id);
        return id;
    }

    /**
     * Считать значение по ключу хеша
     * @param key ключ хеша
     * @return значение хранимое по ключу
     */
    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    /**
     * Считать ВСЕ сообщения
     * @return значения всех сообщений
     */
    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    /**
     * Считать все сообщения с определенным уровнем важности
     * @param level уровень важности по которому будут фильтроваться сообщения
     * @return значения всех сообщений, которые прошли фильтрацию по уровню важности
     */
    @Override
    public Collection<Message> findAllBySeverity(Severity level) {
        return messages.values().stream()
                .filter(message -> message.getSeverity() == level)
                .collect(Collectors.toList());
    }
}
