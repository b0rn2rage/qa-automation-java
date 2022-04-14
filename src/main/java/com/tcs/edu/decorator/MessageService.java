package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Класс-контроллер, который полностью собирает готовое декорированное сообщение для печати
 */
public class MessageService {
    /**
     * Процедура, собирающая готовое декорированное сообщение
     * @param level - Enum, отражающий важность сообщения
     * @param message - Изначальное НЕ декорированное сообщение, которое в последствии декорируется
     */
    public static void buildMessage(Severity level, String message) {
        String decoratedMessage = PrefixDecorator.decorate(message) + " " +
                SeverityDecorator.toString(level);
        ConsolePrinter.print(PageSeparator.separate(decoratedMessage));
    }
}
