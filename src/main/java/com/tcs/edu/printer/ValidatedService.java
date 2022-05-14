package com.tcs.edu.printer;

import com.tcs.edu.domain.Message;

/**
 * Используется для валидации входных параметров
 */
public abstract class ValidatedService {

    /**
     * @param message - сообщение, которое будет провалидировано
     * @return true - если валидация прошла успешно, false - если сообщение не прошло валидацию
     */
    public boolean isArgsValid(Message message) {
        return message != null && message.getBody() != null;
    }
}
