package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

/**
 * Сервисный класс для формирования сообщений
 */
public class MessageService {

    /**
     * Формирует и печатает декорированные сообщения, состоящие из:
     * - префикса (порядковый номер сообщения + timestamp)
     * - самого сообщения
     * - уровня важности
     * <p>
     * Не печатает message = null.
     * Не печатает messages = null.
     * Если level = null, то сообщение не будет декорировано уровнем важности.
     *
     * @param level    - Enum, отражающий важность сообщения
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список дополнительных входящих сообщений
     */
    public static void print(Severity level, String message, String... messages) {

        if (message != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message);
            if (level != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
            }
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }

        if (messages != null) {
            String currentDecoratedMessage;
            for (String currentMessage : messages) {
                if (currentMessage == null) {
                    continue;
                }
                currentDecoratedMessage = PrefixDecorator.addPrefix(currentMessage);
                if (level != null) {
                    currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
                }
                ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
            }
        }
    }

    /**
     * Формирует и печатает декорированные сообщения, состоящие из:
     * - префикса (порядковый номер сообщения + timestamp)
     * - самого сообщения
     * - уровня важности
     * <p>
     * Отличительной особенностью данного метода является то,
     * что он позволяет сортировать сообщения переданные в messages.
     * <p>
     * Не печатает message = null.
     * Не печатает messages = null.
     * Если level = null, то сообщение не будет декорировано уровнем важности.
     * Если order = null, то messages не будут отсортированы
     *
     * @param level    - Enum, отражающий важность сообщения
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список дополнительных входящих сообщений
     */
    public static void print(Severity level, MessageOrder order, String message, String... messages) {

        if (message != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message);
            if (level != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
            }
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }

        if (messages != null) {
            String currentDecoratedMessage;
            if (order == MessageOrder.ASC || order == null) {
                for (String s : messages) {
                    if (s == null) {
                        continue;
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(s);
                    if (level != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            } else if (order == MessageOrder.DESC) {
                for (int counter = messages.length - 1; counter > -1; counter--) {
                    if (messages[counter] == null) {
                        continue;
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(messages[counter]);
                    if (level != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            }
        }

    }

    /**
     * Формирует и печатает декорированные сообщения, состоящие из:
     * - префикса (порядковый номер сообщения + timestamp)
     * - самого сообщения
     * - уровня важности
     * <p>
     * Отличительной особенностью данного метода является то,
     * что он позволяет регулировать уникальность элементов, передаваемых в messages
     * <p>
     * Не печатает message = null.
     * Не печатает messages = null.
     * Если level = null, то сообщение не будет декорировано уровнем важности.
     * Если order = null, то messages не будут отсортированы
     * Если doubling = null, то дубли будут разрешены
     *
     * @param level    - Enum, отражающий важность сообщения
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param doubling - Enum, определяющий будут ли выводиться на печать дублирующиеся элементы из массива messages
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список дополнительных входящих сообщений
     */
    public static void print(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        if (message != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message);
            if (level != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
            }
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }

        if (messages != null) {
            String currentDecoratedMessage;
            // В printedMessages будут хранится НЕ ПОЛНОСТЬЮ напечатанные сообщения с timestamp,
            // а просто список "сырых" messages, которые уже были напечатаны,
            // так как если хранить вместе с timestamp, то дублей не будет никогда
            String[] printedMessages = new String[messages.length];
            int counter = 0;
            if (order == MessageOrder.ASC || order == null) {
                for (String s : messages) {
                    if (s == null) {
                        continue;
                    }
                    if (doubling == Doubling.DISTINCT) {
                        if (Inspector.isUnique(s, printedMessages)) {
                            printedMessages[counter++] = s;
                        } else {
                            continue;
                        }
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(s);
                    if (level != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            } else if (order == MessageOrder.DESC) {
                for (int i = messages.length - 1; i > -1; i--) {
                    if (messages[i] == null) {
                        continue;
                    }
                    if (doubling == Doubling.DISTINCT) {
                        if (Inspector.isUnique(messages[i], printedMessages)) {
                            printedMessages[counter++] = messages[i];
                        } else {
                            continue;
                        }
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(messages[i]);
                    if (level != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(level);
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            }
        }
    }
}
