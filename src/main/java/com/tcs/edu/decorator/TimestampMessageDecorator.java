package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Декорирует полученный текст
 * @author Смирнов Антон
 */
public class TimestampMessageDecorator {
    /**
     * Производит конкатенацию строк, состоит из двух частей:
     * - время в формате ISO8601 +
     * - текст, переданный в метод
     * @param message - Текст, переданный в метод
     * @return Время в формате ISO8601 + {@param message}
     */
    public static String decorate(String message) {
        return Instant.now() + message;
    }
}
