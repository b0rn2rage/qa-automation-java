package com.tcs.edu.domain;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;

public interface MessageService {
    void log(Message message, Message... messages);

    void log(MessageOrder order, Message message, Message... messages);

    void log(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
