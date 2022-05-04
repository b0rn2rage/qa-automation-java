package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
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
     * Не печатает сообщения с message.getBody() = null.
     * Не печатает messages.getBody() = null.
     * Если message.getSeverity()/messages.getSeverity() = null, то сообщение не будет декорировано уровнем важности.
     *
     * @param message  - Сообщение переданное на декорирование и печать, сообщение может содержать уровень значимости
     * @param messages - Список дополнительных входящих сообщений, сообщения могут содержать уровень значимости
     */
    public static void log(Message message, Message... messages) {

        if (message.getBody() != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message.getBody());
            if (message.getSeverity() != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(message.getSeverity());
            }
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }

        if (messages != null) {
            String currentDecoratedMessage;
            for (Message currentMessage : messages) {
                if (currentMessage.getBody() == null) {
                    continue;
                }
                currentDecoratedMessage = PrefixDecorator.addPrefix(currentMessage.getBody());
                if (currentMessage.getSeverity() != null) {
                    currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(currentMessage.getSeverity());
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
     * Не печатает сообщения с message.getBody() = null.
     * Не печатает messages.getBody() = null.
     * Если message.getSeverity()/messages.getSeverity() = null, то сообщение не будет декорировано уровнем важности.
     * Если order = null, то messages не будут отсортированы
     *
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param message  - Сообщение переданное на декорирование и печать, сообщение может содержать уровень значимости
     * @param messages - Список дополнительных входящих сообщений, сообщения могут содержать уровень значимости
     */
    public static void log(MessageOrder order, Message message, Message... messages) {

        if (message.getBody() != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message.getBody());
            if (message.getSeverity() != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(message.getSeverity());
            }
            ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
        }

        if (messages != null) {
            String currentDecoratedMessage;
            if (order == MessageOrder.ASC || order == null) {
                for (Message currentMessage : messages) {
                    if (currentMessage.getBody() == null) {
                        continue;
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(currentMessage.getBody());
                    if (currentMessage.getSeverity() != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(currentMessage.getSeverity());
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            } else if (order == MessageOrder.DESC) {
                for (int counter = messages.length - 1; counter > -1; counter--) {
                    if (messages[counter].getBody() == null) {
                        continue;
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(messages[counter].getBody());
                    if (messages[counter].getSeverity() != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " +
                                SeverityDecorator.toString(messages[counter].getSeverity());
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
     * Не печатает сообщения с message.getBody() = null.
     * Не печатает messages.getBody() = null.
     * Если message.getSeverity()/messages.getSeverity() = null, то сообщение не будет декорировано уровнем важности.
     * Если order = null, то messages не будут отсортированы
     * Если doubling = null, то дубли в messages будут разрешены
     *
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param doubling - Enum, определяющий будут ли выводиться на печать дублирующиеся элементы из messages
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список дополнительных входящих сообщений
     */
    public static void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        if (message.getBody() != null) {
            String currentDecoratedMessage;
            currentDecoratedMessage = PrefixDecorator.addPrefix(message.getBody());
            if (message.getSeverity() != null) {
                currentDecoratedMessage = currentDecoratedMessage + " " + SeverityDecorator.toString(message.getSeverity());
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
                for (Message currentMessage : messages) {
                    if (currentMessage.getBody() == null) {
                        continue;
                    }
                    if (doubling == Doubling.DISTINCT) {
                        if (Inspector.isUnique(currentMessage.getBody(), printedMessages)) {
                            printedMessages[counter++] = currentMessage.getBody();
                        } else {
                            continue;
                        }
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(currentMessage.getBody());
                    if (currentMessage.getSeverity() != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " "
                                + SeverityDecorator.toString(currentMessage.getSeverity());
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            } else if (order == MessageOrder.DESC) {
                for (int i = messages.length - 1; i > -1; i--) {
                    if (messages[i].getBody() == null) {
                        continue;
                    }
                    if (doubling == Doubling.DISTINCT) {
                        if (Inspector.isUnique(messages[i].getBody(), printedMessages)) {
                            printedMessages[counter++] = messages[i].getBody();
                        } else {
                            continue;
                        }
                    }
                    currentDecoratedMessage = PrefixDecorator.addPrefix(messages[i].getBody());
                    if (messages[i].getSeverity() != null) {
                        currentDecoratedMessage = currentDecoratedMessage + " " +
                                SeverityDecorator.toString(messages[i].getSeverity());
                    }
                    ConsolePrinter.print(PageSeparator.separate(currentDecoratedMessage));
                }
            }
        }
    }
}
