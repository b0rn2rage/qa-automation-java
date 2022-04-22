package com.tcs.edu;

import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {
        MessageService.print(Severity.MAJOR, null, null, "Hello World!");
    }
}
