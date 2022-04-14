package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Класс-контроллер, который полностью собирает готовое декорированное сообщение для печати
 */
public class MessageService {
    /**
     * Процедура, собирающая готовое декорированное сообщение
     *
     * @param level   - Enum, отражающий важность сообщения
     * @param messages - Принимает на вход массив НЕ декорированных сообщений,
     *                 которые в последствии декорируются. Длина массива должна быть > 0.
     */
    public static void buildMessage(Severity level, String... messages) {
        int counter = 0;
        do {
            String decoratedMessage = PrefixDecorator.decorate(messages[counter]) + " " +
                    SeverityDecorator.toString(level);
            ConsolePrinter.print(PageSeparator.separate(decoratedMessage));
            counter++;
        } while (counter < messages.length);
    }
}
