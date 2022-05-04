package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.MessageService;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;

class Application {
    public static void main(String[] args) {
        Message message0 = new Message((Severity) null, (String) null);
        Message message1 = new Message((Severity) null, "Hello World!1");
        Message message2 = new Message(Severity.REGULAR, "Hello World!2");
        Message message3 = new Message(Severity.MINOR, "Hello World!3");
        Message message4 = new Message(Severity.MAJOR, "Hello World!4");


        MessageService.log(message0, message1, message2, message3);

        MessageService.log((MessageOrder) null, message1, message2, message3, message4);
        MessageService.log(MessageOrder.ASC, message1, message2, message3, message4);
        MessageService.log(MessageOrder.DESC, message1, message2, message3, message4);

        MessageService.log(MessageOrder.ASC, (Doubling) null, message1, message1, message2, message2);
        MessageService.log(MessageOrder.ASC, Doubling.DOUBLES, message1, message1, message2, message2);
        MessageService.log(MessageOrder.ASC, Doubling.DISTINCT, message1, message1, message2, message2);
        MessageService.log(MessageOrder.DESC, Doubling.DOUBLES, message1, message1, message2, message2);
        MessageService.log(MessageOrder.DESC, Doubling.DISTINCT, message1, message1, message2, message2);
    }
}
