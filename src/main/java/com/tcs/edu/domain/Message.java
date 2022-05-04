package com.tcs.edu.domain;

import com.tcs.edu.decorator.Severity;

/**
 * Класс с текстом сообщения и параметрами сообщения
 */
public class Message {
    private Severity severity;
    private String body;

    /**
     * Конструктор класса
     * @param severity - уровень важности сообщения
     * @param body - текст сообщения
     */
    public Message(Severity severity, String body) {
        this.severity = severity;
        this.body = body;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
