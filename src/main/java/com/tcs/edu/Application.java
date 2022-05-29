package com.tcs.edu;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.SavingMessageService;
import com.tcs.edu.service.SavingMessageServiceImpl;

import java.util.Collection;
import java.util.UUID;

class Application {
    public static void main(String[] args) {
        Message message1 = new Message(Severity.REGULAR, "Hello World!1");
        Message message2 = new Message(Severity.MINOR, "Hello World!2");
        Message message3 = new Message(Severity.MINOR, "Hello World!3");

        SavingMessageService savingMessageService = new SavingMessageServiceImpl();

        UUID generatedKey1 = savingMessageService.log(message1);
        savingMessageService.log(message2);
        savingMessageService.log(message3);

        System.out.println("Считываем сообщение №1 по хеш ключу:");
        System.out.println(savingMessageService.findByPrimaryKey(generatedKey1));

        System.out.println("Считываем абсолютно все сообщения:");
        final Collection<Message> allMessages = savingMessageService.findAll();
        for (Message currentMessage : allMessages) {
            System.out.println(currentMessage);
        }

        System.out.println("Считываем сообщения с уровнем важности = MINOR:");
        System.out.println(savingMessageService.findAllBySeverity(Severity.MINOR));



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
