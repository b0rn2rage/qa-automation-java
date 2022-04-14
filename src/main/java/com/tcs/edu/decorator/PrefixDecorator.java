package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс, который формирует префикс для сообщения
 */
public class PrefixDecorator {
    private static int messageCount = 0;

    /**
     * Добавляет префикс к переданному сообщению.
     * Префикс состоит из порядкового номера сообщения и временной метки
     *
     * @param message - сообщение, которому добавится префикс
     * @return сообщение с префиксом
     */
    public static String decorate(String message) {
        messageCount ++;
        return String.format("%d %s %s", messageCount, Instant.now(), message);
    }
}

