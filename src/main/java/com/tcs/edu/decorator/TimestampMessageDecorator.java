package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Декорирует полученный текст
 * @author Смирнов Антон
 */
public class TimestampMessageDecorator {
    private static int messageCount = 0;
    private static final int PAGE_SIZE = 2;
    /**
     * Декорирует переданное сообщение при помощи метода String.format
     * @param message - Текст, переданный в метод
     * @return декорированное сообщение
     */
    public static String decorate(String message) {
        messageCount ++;
        var decoratedMessage = String.format(
                "%d %s %s", messageCount, Instant.now(), message);
        if (messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format(
                    "%d %s %s %s", messageCount, Instant.now(), message, "\n ---");
        }
        return decoratedMessage;
    }
}

