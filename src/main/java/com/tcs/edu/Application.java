package com.tcs.edu;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {
        MessageService.print(Severity.MINOR, MessageOrder.DESC, null,
                "Hello World! 1", "Hello World! 2", "Hello World! 3");
    }
}
