package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * Интерфейс, применяемый для декорирования сообщений
 */
public interface MessageDecorator {
    String decorate(Message message);

    String mapSeverity(Message message);
}
