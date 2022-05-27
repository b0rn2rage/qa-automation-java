package com.tcs.edu;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.SavingMessageService;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import com.tcs.edu.service.SavingMessageServiceImpl;

import java.util.UUID;

class Application {
    public static void main(String[] args) {
        Message message0 = new Message((Severity) null, (String) null);
        Message message1 = new Message(Severity.MAJOR, null);
        Message message2 = new Message((Severity) null, "Hello World!2");
        Message message3 = new Message(Severity.REGULAR, "Hello World!3");
        Message message4 = new Message(Severity.MINOR, "Hello World!4");

        SavingMessageService savingMessageService = new SavingMessageServiceImpl();

//        UUID generatedKey0 = savingMessageService.log(message0);
//        UUID generatedKey1 = savingMessageService.log(message1);
//        UUID generatedKey2 = savingMessageService.log(message2);
        UUID generatedKey3 = savingMessageService.log(message3);
        UUID generatedKey4 = savingMessageService.log(message4);
//        System.out.println(savingMessageService.findByPrimaryKey(generatedKey0));
//        System.out.println(savingMessageService.findByPrimaryKey(generatedKey1));
//        System.out.println(savingMessageService.findByPrimaryKey(generatedKey2));
        System.out.println(savingMessageService.findByPrimaryKey(generatedKey3));
        System.out.println(savingMessageService.findByPrimaryKey(generatedKey4));



//        MessageService service = new OrderedDistinctedMessageService(
//                new ConsolePrinter(),
//                new MessageDecoratorImpl(),
//                new PageSeparator()
//        );
//        service.log((MessageOrder) null, message1, message2, message3);
//        service.log(MessageOrder.ASC, message1, message2, message3);
//        service.log(MessageOrder.DESC, message1, message2, message3, message4);
//
//        service.log(MessageOrder.ASC, (Doubling) null, message1, message2, message3, message4);
//        service.log(MessageOrder.ASC, Doubling.DISTINCT, message1, message2, message2, message2);
//        service.log(MessageOrder.ASC, Doubling.DOUBLES, message1, message2, message2, message2);
//        service.log(MessageOrder.DESC, Doubling.DISTINCT, message1, message2, message3, message3);
//        service.log(MessageOrder.DESC, Doubling.DOUBLES, message1, message2, message3, message3);
//
//        System.out.println(message5);
//        System.out.println(message5.equals(message3));
//        System.out.println(message5.equals(message4));
//        System.out.println(message5.hashCode());

//        service.log(message0, message1, message2, message3, message4); // negative scenario
//        service.log(message1, message0); // negative scenario
//        service.log(null);
    }
}
