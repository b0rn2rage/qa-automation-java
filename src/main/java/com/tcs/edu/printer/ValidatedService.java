package com.tcs.edu.printer;

import com.tcs.edu.domain.Message;

/**
 * Базовый класс, содержит методы валидации параметров
 */
public abstract class ValidatedService {

    /**
     * @param message - сообщение, которое будет провалидировано
     */
    public void isArgsValid(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (message.getBody() == null){
            throw new NullPointerException("message.getBody() is null");
        }
    }
}
