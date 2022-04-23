package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {

        MessageService.print((Severity) null, (String) null, null, "Hello World!", "Hello World!");
        MessageService.print(Severity.MINOR, "random message", "Hello World! 1",
                "Hello World! 2", "Hello World! 3");


        MessageService.print(Severity.REGULAR, MessageOrder.ASC, "random message", "Hello World! 1",
                "Hello World! 2", "Hello World! 3");
        MessageService.print(Severity.MAJOR, MessageOrder.DESC, "random message", "Hello World! 1",
                "Hello World! 2", "Hello World! 3");

        MessageService.print(Severity.MAJOR, MessageOrder.ASC, Doubling.DOUBLES, "random message", "Hello World! 1",
                "Hello World! 2", "Hello World! 3");
        MessageService.print(Severity.MAJOR, MessageOrder.ASC, Doubling.DISTINCT, "random message", "Hello World! 1",
                "Hello World! 2", "Hello World! 3");
    }
}
