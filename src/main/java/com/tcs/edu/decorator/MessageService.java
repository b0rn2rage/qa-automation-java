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
     * @param message - сообщение переданное на декорирование и печать
     * @param messages - Список входящих сообщений
     */
    public static void print(Severity level, String message, String... messages) {
        String[] allMessages = new String[messages.length + 1]; // +1 добавляем так как есть обязательный message
        allMessages[0] = message; // нулевой элемент массива = обязательный аргумент message

        // Заполняем массив allMessages элементами из массива messages
        for(int i = 1; i < messages.length + 1; i++) {
            for (String current : messages) {
                allMessages[i] = current;
            }
        }

        for (String s : allMessages) {
            String currentDecoratedMessage = PrefixDecorator.decorate(s) + " " +
                    SeverityDecorator.toString(level);
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }
    }
}
