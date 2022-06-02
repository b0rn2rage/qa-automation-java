package com.tcs.edu.service;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.*;
import com.tcs.edu.printer.Printer;


/**
 * Сервисный класс для формирования сообщений
 */
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private final Printer printer;
    private final MessageDecorator messageDecorator;
    private final PageDecorator pageDecorator;

    public OrderedDistinctedMessageService(Printer printer,
                                           MessageDecorator messageDecorator,
                                           PageDecorator pageDecorator) {
        this.printer = printer;
        this.messageDecorator = messageDecorator;
        this.pageDecorator = pageDecorator;
    }

    /**
     * Формирует и печатает декорированные сообщения, состоящие из:
     * - префикса (порядковый номер сообщения + timestamp)
     * - самого сообщения
     * - уровня важности
     * <p>
     * Если поле body класса Message имеет значение = null, то сообщение не будет выведено на печать
     * Если поле severity класса Message имеет значение = null, то сообщение не будет декорировано уровнем важности
     *
     * @param message  - Сообщение переданное на декорирование и печать, сообщение может содержать уровень значимости
     * @param messages - Список дополнительных входящих сообщений, сообщения могут содержать уровень значимости
     */
    public void log(Message message, Message... messages) {
        this.log(MessageOrder.ASC, Doubling.DOUBLES, message, messages);
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
     * Если поле body класса Message имеет значение = null, то сообщение не будет выведено на печать
     * Если поле severity класса Message имеет значение = null, то сообщение не будет декорировано уровнем важности
     * Если order = null, то messages не будут отсортированы
     *
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param message  - Сообщение переданное на декорирование и печать, сообщение может содержать уровень значимости
     * @param messages - Список дополнительных входящих сообщений, сообщения могут содержать уровень значимости
     */
    public void log(MessageOrder order, Message message, Message... messages) {
        this.log(order, Doubling.DOUBLES, message, messages);
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
     * Если поле body класса Message имеет значение = null, то сообщение не будет выведено на печать
     * Если поле severity класса Message имеет значение = null, то сообщение не будет декорировано уровнем важности
     * Если order = null, то messages не будут отсортированы
     * Если doubling = null, то дубли в messages будут разрешены
     *
     * @param order    - Enum, регулирующий метод сортировки сообщений
     * @param doubling - Enum, определяющий будут ли выводиться на печать дублирующиеся элементы из messages
     * @param message  - Сообщение переданное на декорирование и печать
     * @param messages - Список дополнительных входящих сообщений
     */
    public void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new LogException("message = null", e);
        }
        String currentDecoratedMessage;
        currentDecoratedMessage = messageDecorator.decorate(message);
        printer.print(pageDecorator.decorate(currentDecoratedMessage));

        // В printedMessages будут хранится НЕ ПОЛНОСТЬЮ напечатанные сообщения с timestamp,
        // а просто список "сырых" messages, которые уже были напечатаны,
        // так как если хранить вместе с timestamp, то дублей не будет никогда
        String[] printedMessages = new String[messages.length];
        int counter = 0;
        if (order == MessageOrder.ASC || order == null) {
            for (Message currentMessage : messages) {
                try {
                    super.isArgsValid(currentMessage);
                } catch (IllegalArgumentException | NullPointerException e) {
                    throw new LogException("message = null", e);
                }
                if (doubling == Doubling.DISTINCT) {
                    if (Inspector.isUnique(currentMessage.getBody(), printedMessages)) {
                        printedMessages[counter++] = currentMessage.getBody();
                    } else {
                        continue;
                    }
                }
                currentDecoratedMessage = messageDecorator.decorate(currentMessage);
                printer.print(pageDecorator.decorate(currentDecoratedMessage));
            }
        } else if (order == MessageOrder.DESC) {
            for (int i = messages.length - 1; i > -1; i--) {
                try {
                    super.isArgsValid(messages[i]);
                } catch (IllegalArgumentException | NullPointerException e) {
                    throw new LogException("message = null", e);
                }
                if (doubling == Doubling.DISTINCT) {
                    if (Inspector.isUnique(messages[i].getBody(), printedMessages)) {
                        printedMessages[counter++] = messages[i].getBody();
                    } else {
                        continue;
                    }
                }
                currentDecoratedMessage = messageDecorator.decorate(messages[i]);
                printer.print(pageDecorator.decorate(currentDecoratedMessage));
            }
        }
    }
}
