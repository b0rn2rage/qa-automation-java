package com.tcs.edu.domain;

import com.tcs.edu.decorator.Severity;

/**
 * Класс с текстом сообщения и параметрами сообщения
 */
public class Message {
    private final Severity severity;
    private final String body;

    /**
     * Конструктор класса
     * @param severity - уровень важности сообщения
     * @param body - текст сообщения
     */
    public Message(Severity severity, String body) {
        this.severity = severity;
        this.body = body;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getBody() {
        return body;
    }
}
