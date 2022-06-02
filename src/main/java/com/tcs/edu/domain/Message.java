package com.tcs.edu.domain;

import com.tcs.edu.decorator.Severity;

import java.util.Objects;
import java.util.UUID;

/**
 * Класс с текстом сообщения и параметрами сообщения
 */
public class Message {
    private final Severity severity;
    private final String body;
    private UUID id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "severity=" + severity +
                ", body='" + body + '\'' +
                '}';
    }

}
