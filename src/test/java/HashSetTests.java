import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.SavingMessageService;
import com.tcs.edu.service.SavingMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HashSetTests {
    private SavingMessageService savingMessageService;
    private Message message1;
    private Message message2;
    private Message message3;

    @BeforeEach
    public void setUp() {
        savingMessageService = new SavingMessageServiceImpl();
    }

    @Test
    @DisplayName("Positive. Get message by hash key")
    public void getMessageByHashKey() {
        //region Given
        message1 = new Message(Severity.REGULAR, "Hello World!1");
        UUID generatedKey1 = savingMessageService.log(message1);
        //endregion
        //region When
        //endregion
        //region Then
        Assertions.assertEquals(message1.getBody(), savingMessageService
                .findByPrimaryKey(generatedKey1)
                .getBody());
        Assertions.assertEquals(message1.getSeverity(), savingMessageService
                .findByPrimaryKey(generatedKey1)
                .getSeverity());
        //endregion
    }

    @Test
    @DisplayName("Negative. Try get message when message severity and body is null")
    public void tryGetKeyWhenMessageSeverityAndBodyIsNull() {
        //region Given
        message1 = new Message(null, null);
        //endregion
        //region When
        //endregion
        //region Then
        assertThatThrownBy(
                () -> savingMessageService.log(message1)
        ).isInstanceOf(LogException.class)
                .hasCauseInstanceOf(NullPointerException.class)
                .hasMessage("message = null");
        //endregion
    }

    @Test
    @DisplayName("Positive. Get all messages")
    public void getAllMessages() {
        //region Given
        message1 = new Message(Severity.REGULAR, "Hello World!1");
        message2 = new Message(Severity.MINOR, "Hello World!2");
        message3 = new Message(Severity.MINOR, "Hello World!3");
        savingMessageService.log(message1);
        savingMessageService.log(message2);
        savingMessageService.log(message3);
        //endregion
        //region When
        Collection<Message> allMessages = savingMessageService.findAll();
        //endregion
        //region Then
        assertThat(allMessages, containsInAnyOrder(message1, message2, message3));
        assertThat(allMessages, hasSize(3));
        //endregion
    }

    @Test
    @DisplayName("Positive. Get messages by severity")
    public void getMessagesBySeverity() {
        //region Given
        message1 = new Message(Severity.REGULAR, "Hello World!1");
        message2 = new Message(Severity.MINOR, "Hello World!2");
        message3 = new Message(Severity.MINOR, "Hello World!3");
        savingMessageService.log(message1);
        savingMessageService.log(message2);
        savingMessageService.log(message3);
        //endregion
        //region When
        Collection<Message> messagesBySeverity = savingMessageService.findAllBySeverity(Severity.MINOR);
        //endregion
        //region Then
        assertThat(messagesBySeverity, containsInAnyOrder(message2, message3));
        Assertions.assertFalse(messagesBySeverity.contains(message1));
        assertThat(messagesBySeverity, hasSize(2));
        //endregion
    }

    @Test
    @DisplayName("Positive. Check creating key via log method")
    public void checkCreatingHashKey() {
        //region Given
        message1 = new Message(Severity.REGULAR, "Hello World!");
        message2 = new Message(Severity.MINOR, "Random text");
        //endregion
        //region When
        UUID generatedKey1 = savingMessageService.log(message1);
        UUID generatedKey2 = savingMessageService.log(message2);
        //endregion
        //region Then
        Assertions.assertNotEquals(generatedKey1, generatedKey2);
        //endregion
    }
}
