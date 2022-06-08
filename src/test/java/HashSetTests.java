import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.SavingMessageService;
import com.tcs.edu.service.SavingMessageServiceImpl;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HashSetTests {
    private SavingMessageService emptySavingMessageService;
    private Message message1;
    private Message message2;
    private Message message3;

    @BeforeEach
    public void initForAllClasses() {
        emptySavingMessageService = new SavingMessageServiceImpl();
        message1 = new Message(Severity.REGULAR, "Hello World!");
        message2 = new Message(Severity.REGULAR, "Hello World!");
        message3 = new Message(Severity.MAJOR, "Hello World!");
    }

    @Nested
    @DisplayName("Message search tests")
    class ForSearchMessageTests {
        private SavingMessageService savingMessageServiceWithOneMessage;
        private SavingMessageService savingMessageServiceWithTwoMessages;

        private UUID generatedKey1;
        private UUID generatedKey2;
        private UUID generatedKey3;

        private UUID nonExistsUUID;

        @BeforeEach
        public void initData() {
            savingMessageServiceWithOneMessage = new SavingMessageServiceImpl();
            savingMessageServiceWithTwoMessages = new SavingMessageServiceImpl();

            generatedKey1 = savingMessageServiceWithOneMessage.log(message1);
            generatedKey2 = savingMessageServiceWithTwoMessages.log(message2);
            generatedKey3 = savingMessageServiceWithTwoMessages.log(message3);

            nonExistsUUID = UUID.randomUUID();
        }

        @Nested
        class FindByPrimaryKeyTests {

            @Test
            @DisplayName("Positive. Get message by hash key")
            public void getMessageByHashKey() {
                Assertions.assertEquals(message1.getBody(), savingMessageServiceWithOneMessage
                        .findByPrimaryKey(generatedKey1)
                        .getBody());
                Assertions.assertEquals(message1.getSeverity(), savingMessageServiceWithOneMessage
                        .findByPrimaryKey(generatedKey1)
                        .getSeverity());
            }

            @Test
            @DisplayName("Negative. Attempt to get a key by a non-existent UUID")
            public void tryGetMessageWhenUUIDisNonExists() {
                Assertions.assertNull(emptySavingMessageService.findByPrimaryKey(nonExistsUUID));
            }

            @Test
            @DisplayName("Negative. Trying get message from someone else savingMessageService")
            public void tryGetMessageFromSomeoneElseSavingMessageService() {
                Assertions.assertNull(savingMessageServiceWithOneMessage.findByPrimaryKey(generatedKey2));
                Assertions.assertNull(savingMessageServiceWithOneMessage.findByPrimaryKey(generatedKey3));
            }
        }

        @Nested
        class FindAllTests {
            @Test
            @DisplayName("Positive. Get all messages from different savingMessageServices")
            public void getAllMessagesFromDifferentSavingMessageServices() {
                assertThat(emptySavingMessageService.findAll(), hasSize(0));
                assertThat(savingMessageServiceWithOneMessage.findAll(), hasSize(1));
                assertThat(savingMessageServiceWithTwoMessages.findAll(), hasSize(2));
                assertThat(savingMessageServiceWithOneMessage.findAll(), contains(message1));
                assertThat(savingMessageServiceWithTwoMessages.findAll(), containsInAnyOrder(message2, message3));
            }
        }

        @Nested
        class FindBySeverityTests {
            @Test
            @DisplayName("Positive. Get messages by severity")
            public void getMessagesBySeverity() {
                assertThat(emptySavingMessageService.findAllBySeverity(null), hasSize(0));
                assertThat(savingMessageServiceWithOneMessage.findAllBySeverity(Severity.REGULAR), hasSize(1));
                assertThat(savingMessageServiceWithTwoMessages.findAllBySeverity(Severity.MAJOR), hasSize(1));
            }
        }
    }

    @Nested
    class ForLogMethodTests {
        private Message message4;

        @BeforeEach
        public void initData() {
            Message message4 = new Message(null, null);
        }

        @Test
        @DisplayName("Positive. Check that after calling 'log' the size of collection increases by 1")
        public void checkInsertMessagesInHashSet() {
            assertThat(emptySavingMessageService.findAll(), hasSize(0));
            emptySavingMessageService.log(message1);
            assertThat(emptySavingMessageService.findAll(), hasSize(1));
            emptySavingMessageService.log(message2);
            assertThat(emptySavingMessageService.findAll(), hasSize(2));
            emptySavingMessageService.log(message3);
            assertThat(emptySavingMessageService.findAll(), hasSize(3));
        }

        @Test
        @DisplayName("Positive. if we call the 'method' 2 times with the same message, " +
                "then the size of the collection should increase by 2")
        public void callLogWithSameMessages() {
            emptySavingMessageService.log(message1);
            emptySavingMessageService.log(message1);
            assertThat(emptySavingMessageService.findAll(), hasSize(2));
        }

        @Test
        @DisplayName("Negative. If message.getBody() = null, then we throw an IllegalArgumentException")
        public void shouldThrowLogExceptionWhenMessageBodyIsNull() {
            assertThatThrownBy(
                    () -> emptySavingMessageService.log(message4)
            ).isInstanceOf(LogException.class)
                    .hasCauseInstanceOf(IllegalArgumentException.class)
                    .hasMessage("message = null");
        }

        @Test
        @DisplayName("Negative. If message = null, then we throw an NullPointerException")
        public void shouldThrowLogExceptionWhenMessageIsNull() {
            assertThatThrownBy(
                    () -> emptySavingMessageService.log(null)
            ).isInstanceOf(LogException.class)
                    .hasCauseInstanceOf(IllegalArgumentException.class)
                    .hasMessage("message = null");
        }
    }
}
