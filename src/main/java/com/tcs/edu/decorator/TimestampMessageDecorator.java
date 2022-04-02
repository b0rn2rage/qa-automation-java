package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Декорирует полученный текст
 * @author Смирнов Антон
 */
public class TimestampMessageDecorator {
    private static int messageCount = 0;
    /**
     * Декорирует переданное сообщение при помощи конкатенации
     * @param message - Текст, переданный в метод
     * @return порядковый номер сообщения + время в формате ISO8601 + {@param message}
     */
    public static String decorate(String message) {
        messageCount ++;
        final var decoratedMessage = messageCount + " " + Instant.now() + message;
        return decoratedMessage;
    }
}

