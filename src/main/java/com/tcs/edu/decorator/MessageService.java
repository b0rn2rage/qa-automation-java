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
     * @param order    - Каким методом будет происходить сортировка массива messages при выводе на печать
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список входящих сообщений
     */
    public static void print(Severity level, MessageOrder order, String message, String... messages) {
        String[] allMessages = new String[messages.length + 1];
        int counter = 0;

        // Если message != null, то первый элемент массива = message
        if (message != null) {
            allMessages[counter++] = message;
        }

        for (String current : messages) {
            // Если элемент массива messages = null, то НЕ складываем его в массив allMessages
            if (current != null) {
                switch (order) {
                    case ASC:
                        allMessages[counter++] = current;
                        break;
                    case DESC:
                        // Обработка в зависимости от того, передавался ли message в метод
                        if (allMessages[0] != null) {
                            allMessages[messages.length + 1 - counter++] = current;
                        } else {
                            allMessages[messages.length - counter++] = current;
                        }
                        break;
                }
            }
        }

        for (String s : allMessages) {
            // Если в массиве остались null элементы, то НЕ выводим их на печать
            if (s != null) {
                String currentDecoratedMessage = PrefixDecorator.decorate(s) + " " +
                        SeverityDecorator.toString(level);
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
            }
        }
    }
}
