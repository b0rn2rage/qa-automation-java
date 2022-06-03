import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.SavingMessageService;
import com.tcs.edu.service.SavingMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

public class HashSetTests {
    private static SavingMessageService savingMessageService;
    private static Message message1;
    private static Message message2;
    private static Message message3;

    @BeforeEach
    public void setUp() {
        message1 = new Message(Severity.REGULAR, "Hello World!1");
        message2 = new Message(Severity.MINOR, "Hello World!2");
        message3 = new Message(Severity.MINOR, "Hello World!3");
        savingMessageService = new SavingMessageServiceImpl();
    }

    @Test
    @DisplayName("Get message by hash key")
    public void GetMessageByHashKey() {
        //region Given
        //endregion
        //region When
        UUID generatedKey1 = savingMessageService.log(message1);
        //endregion
        //region Then
        Assertions.assertEquals(message1, savingMessageService.findByPrimaryKey(generatedKey1));
        //endregion
    }

    @Test
    @DisplayName("Get all messages")
    public void GetAllMessages() {
        //region Given
        //endregion
        //region When
        savingMessageService.log(message1);
        savingMessageService.log(message2);
        savingMessageService.log(message3);
        Collection<Message> AllMessages = savingMessageService.findAll();
        //endregion
        //region Then
        Assertions.assertTrue(AllMessages.contains(message1));
        Assertions.assertTrue(AllMessages.contains(message2));
        Assertions.assertTrue(AllMessages.contains(message3));
        Assertions.assertEquals(3, AllMessages.size());
        //endregion
    }

    @Test
    @DisplayName("Get messages by severity")
    public void GetMessagesBySeverity() {
        //region Given
        //endregion
        //region When
        savingMessageService.log(message1);
        savingMessageService.log(message2);
        savingMessageService.log(message3);
        Collection<Message> MessagesBySeverity = savingMessageService.findAllBySeverity(Severity.MINOR);
        //endregion
        //region Then
        Assertions.assertFalse(MessagesBySeverity.contains(message1));
        Assertions.assertTrue(MessagesBySeverity.contains(message2));
        Assertions.assertTrue(MessagesBySeverity.contains(message3));
        Assertions.assertEquals(2, MessagesBySeverity.size());
        //endregion
    }
}
