package com.tcs.edu;

import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {
        MessageService.buildMessage(Severity.MAJOR, "Hello World!");
        MessageService.buildMessage(Severity.MINOR, "Hello World!");
        MessageService.buildMessage(Severity.REGULAR, "Hello World!");
        MessageService.buildMessage(Severity.MAJOR, "Hello World!");
        MessageService.buildMessage(Severity.MINOR, "Hello World!");
        MessageService.buildMessage(Severity.REGULAR, "Hello World!");
    }
}
