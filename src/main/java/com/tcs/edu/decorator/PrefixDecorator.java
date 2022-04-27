package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Содержит методы по формированию префикса для сообщений
 */
public class PrefixDecorator {
    private static int sequenceNumber = 0;

    /**
     * Добавляет префикс к переданному сообщению.
     * Префикс состоит из порядкового номера сообщения и временной метки
     * Каждый раз при вызове метода счетчик sequenceNumber увеличивается на единицу
     *
     * @param message - сообщение, к которому добавится префикс
     * @return сообщение с префиксом
     */
    public static String addPrefix(String message) {
        sequenceNumber++;
        return String.format("%d %s %s", sequenceNumber, Instant.now(), message);
    }
}

