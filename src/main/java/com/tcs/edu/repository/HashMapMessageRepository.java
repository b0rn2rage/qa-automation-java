package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Класс, реализующий методы для хеширования сообщений
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
     * Достать значение по ключу хеша
     * @param key ключ хеша
     * @return значение хранимое по ключу
     */
    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }
}
