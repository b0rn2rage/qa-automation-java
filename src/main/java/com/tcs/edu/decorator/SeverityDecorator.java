package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;

/**
 * Содержит методы по декорированию enum'a
 */
public class SeverityDecorator implements MessageDecorator {
    /**
     * Возвращает строковое значение для enum Severity
     * @param message - экземпляр класса Message
     * @return - декорированный уровень важности сообщения в строковом формате
     */
    public String decorate(Message message) {
        String stringSeverity;
        switch (message.getSeverity()) {
            case MAJOR:
                stringSeverity = "(!!!)";
                break;
            case MINOR:
                stringSeverity = "()";
                break;
            case REGULAR:
                stringSeverity = "(!)";
                break;
            default:
                stringSeverity = "";
        }
        return stringSeverity;
    }
}
