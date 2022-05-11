package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;

import java.time.Instant;

/**
 * Содержит методы по декорированию сообщений
 */
public class MessageDecoratorImpl implements MessageDecorator {
    private static int sequenceNumber = 0;

    /**
     * Декорирует сообщение
     * Декорирование состоит из:
     * - порядкового номера сообщения
     * - временной метки
     * - уровня важности сообщения
     *
     * Каждый раз при вызове метода счетчик sequenceNumber увеличивается на единицу
     * @param message - сообщение, которое будет продекорированно
     * @return продекорированное сообщение
     */
    public String decorate(Message message) {
        sequenceNumber++;
        return String.format("%d %s %s %s", sequenceNumber, Instant.now(), message.getBody(), mapSeverity(message));
    }

    /**
     * Маппинг уровня важности сообщения
     * @param message - сообщение
     * @return результат маппинга уровня важности сообщения
     */
    public String mapSeverity(Message message) {
        String stringSeverity;
        if (message.getSeverity() == null) {
            stringSeverity = "";
            return stringSeverity;
        }
        switch (message.getSeverity()) {
            case MAJOR:
                stringSeverity = "(!!!)";
                break;
            case MINOR:
                stringSeverity = "()";
                break;
            case REGULAR:
                stringSeverity = "(!)";
                break;
            default:
                stringSeverity = "";
        }
        return stringSeverity;
    }
}

