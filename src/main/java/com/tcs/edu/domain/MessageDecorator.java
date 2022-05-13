package com.tcs.edu.domain;

/**
 * Интерфейс, применяемый для декорирования самих сообщений
 */
public interface MessageDecorator {
    String decorate(Message message);
}
