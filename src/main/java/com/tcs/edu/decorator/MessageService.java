package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Сервисный класс для формирования сообщений
 */
public class MessageService {
    /**
     * Декорирует и печатает сообщения
     *
     * @param level   - Enum, отражающий важность сообщения
     * @param messages - Список входящих сообщений
     */
    public static void decorateAndPrintMessage(Severity level, String... messages) {
        int counter = 0;
        do {
            String decoratedMessage = PrefixDecorator.decorate(messages[counter]) + " " +
                    SeverityDecorator.toString(level);
            ConsolePrinter.print(PageSeparator.separate(decoratedMessage));
            counter++;
        } while (counter < messages.length);
    }
}
