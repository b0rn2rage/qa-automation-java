package com.tcs.edu.service;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.domain.Message;

/**
 * Интерфейс с методами, которые должен содержать каждый сервис по обработке сообщений
 */
public interface MessageService {
    void log(Message message, Message... messages);

    void log(MessageOrder order, Message message, Message... messages);

    void log(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
