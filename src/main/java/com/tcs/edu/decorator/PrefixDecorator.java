package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;

import java.time.Instant;

/**
 * Содержит методы по формированию префикса для сообщений
 */
public class PrefixDecorator implements MessageDecorator {
    private static int sequenceNumber = 0;

    /**
     * Добавляет префикс к переданному сообщению.
     * Префикс состоит из порядкового номера сообщения и временной метки
     * Каждый раз при вызове метода счетчик sequenceNumber увеличивается на единицу
     *
     * @param message - экземпляр класса Message
     * @return сообщение с префиксом
     */
    public String decorate(Message message) {
        sequenceNumber++;
        return String.format("%d %s %s", sequenceNumber, Instant.now(), message.getBody());
    }
}

