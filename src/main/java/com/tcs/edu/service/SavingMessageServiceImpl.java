package com.tcs.edu.service;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;
import com.tcs.edu.repository.SavingMessageService;

import java.util.UUID;

/**
 * Класс, реализующий методы сохранения сообщений
 */
public class SavingMessageServiceImpl extends ValidatedService implements SavingMessageService {
    private final MessageRepository messageRepository = new HashMapMessageRepository();

    /**
     * Сохранить хэш сообщения в хешмапу
     * @param message сообщение
     * @return хэш ключа
     */
    public UUID log(Message message) {
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new LogException("message = null", e);
        }
        return messageRepository.create(message);
    }

    /**
     * Достать значение по ключу
     * @param key ключ
     * @return значение
     */
    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }
}
