package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Сервисный класс для формирования сообщений
 */
public class MessageService {
    /**
     * Декорирует и печатает сообщения
     *
     * @param level    - Enum, отражающий важность сообщения
     * @param message  - сообщение переданное на декорирование и печать
     * @param messages - Список входящих сообщений
     */
    public static void print(Severity level, String message, String... messages) {
        String[] allMessages = new String[messages.length + 1]; //
        int counter = 0;
        if (message != null) {
            allMessages[counter++] = message;
        }
        for (String current : messages) {
            if (current != null) {
                allMessages[counter++] = current;
            }
        }

        for (String s : allMessages) {
            if (s != null) {
                String currentDecoratedMessage = PrefixDecorator.decorate(s) + " " +
                        SeverityDecorator.toString(level);
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
            }
        }
    }
}
