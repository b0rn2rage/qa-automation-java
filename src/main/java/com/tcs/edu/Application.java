package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {
        MessageService.print((Severity) null, (String) null, (String[]) null);
        MessageService.print(Severity.MAJOR, "Hello World!", (String[]) null);
        MessageService.print(Severity.MINOR, (String) null, "Hello World! 1", "Hello World! 2");
        MessageService.print(Severity.MINOR, "Hello World! 1", "Hello World! 2", "Hello World! 3");

        MessageService.print((Severity) null, (MessageOrder) null, (String) null, (String[]) null);
        MessageService.print((Severity) null, (MessageOrder) null, "Hello World!",
                "Hello World! 1", "Hello World! 2", "Hello World! 3");
        MessageService.print(Severity.MAJOR, MessageOrder.ASC, "Hello World!",
                "Hello World! 1", "Hello World! 2", "Hello World! 3");
        MessageService.print(Severity.MINOR, MessageOrder.DESC, "Hello World!",
                "Hello World! 1", "Hello World! 2", "Hello World! 3");

        MessageService.print(Severity.MINOR, MessageOrder.ASC, Doubling.DISTINCT, "Hello World!",
                "Hello World! 1", "Hello World! 1", "Hello World! 2");
        MessageService.print(Severity.MINOR, MessageOrder.ASC, Doubling.DOUBLES, "Hello World!",
                "Hello World! 1", "Hello World! 1", "Hello World! 2");
        MessageService.print(Severity.MINOR, MessageOrder.DESC, Doubling.DISTINCT, "Hello World!",
                "Hello World! 1", "Hello World! 1", "Hello World! 2");
        MessageService.print(Severity.MINOR, MessageOrder.DESC, Doubling.DOUBLES, "Hello World!",
                "Hello World! 1", "Hello World! 1", "Hello World! 2");

    }
}
