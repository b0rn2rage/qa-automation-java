package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Сервисный класс для формирования сообщений
 */
public class MessageService {

    /**
     * @param level    - Enum, отражающий важность сообщения
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список входящих сообщений
     */
    public static void print(Severity level, String message, String... messages) {
        String[] allMessages = new String[messages.length + 1];
        int counter = 0;

        // Если message != null, то первый элемент массива = message
        if (message != null) {
            allMessages[counter++] = message;
        }

        for (String current : messages) {
            // Если элемент массива messages = null, то НЕ складываем его в массив allMessages
            if (current != null) {
                allMessages[counter++] = current;
            }
        }

        for (String s : allMessages) {
            // Если в массиве остались null элементы, то НЕ выводим их на печать
            if (s != null) {
                String currentDecoratedMessage;
                if (level != null) {
                    currentDecoratedMessage = PrefixDecorator.decorate(s) + " " +
                            SeverityDecorator.toString(level);
                } else {
                    currentDecoratedMessage = PrefixDecorator.decorate(s);
                }
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
            }
        }

    }


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

        // В этой реализации метода print мы всегда передаем message,
        // поэтому он всегда является первым элементом массива
        allMessages[0] = message;
        int counter = 0;

        // Заполнить массив allMessages элементами из массива messages в зависимости от значения MessageOrder
        while (counter < messages.length) {
            switch (order) {
                case ASC:
                    for (int i = 1; i < messages.length + 1; i++) {
                        allMessages[i] = messages[counter++];
                    }
                    break;
                case DESC:
                    for (int i = messages.length - 1; i > -1; i--) {
                        allMessages[i] = messages[counter++];
                    }
                    break;
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

    /**
     * Декорирует и печатает сообщения
     *
     * @param level    - Enum, отражающий важность сообщения
     * @param order    - Каким методом будет происходить сортировка массива messages при выводе на печать
     * @param doubling - Определяет можно ли печатать на консоль одинаковые сообщения из messages
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список входящих сообщений
     */
    public static void print(Severity level, MessageOrder order, Doubling doubling,
                             String message, String... messages) {
        String[] allMessages = new String[messages.length + 1];

        // В этой реализации метода print мы всегда передаем message,
        // поэтому он всегда является первым элементом массива
        allMessages[0] = message;
        int counter = 0;

        // Заполнить массив allMessages элементами из массива messages
        while (counter < messages.length) {
            for (int i = 1; i < messages.length + 1; i++) {
                allMessages[i] = messages[counter++];
            }
        }

        for(int x = 0; x < allMessages.length; x ++) {

            String currentDecoratedMessage = PrefixDecorator.decorate(allMessages[x]) + " " +
                    SeverityDecorator.toString(level);

            if(doubling == Doubling.DISTINCT) {
                String[] printedMessages = new String[messages.length + 1];

                // 1ое сообщение печатаем полюбому, оно не может быть дубликатом
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                printedMessages[x] = currentDecoratedMessage;

                // Итерируемся по списку напечатанных сообщений
                // Если currentDecoratedMessage еще не печаталось, то печатаем его, иначе - игнорируем
                for(String printMessage : printedMessages) {
                    if(printMessage != null && !currentDecoratedMessage.equals(printMessage)) {
                        ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                        printedMessages[x] = currentDecoratedMessage;
                    }
                }
            } else if(doubling == Doubling.DOUBLES) {
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
            }
        }
    }
}
