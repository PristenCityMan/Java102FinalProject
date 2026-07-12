package dialog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.homework_05.dialog.Dialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DialogTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void testAnswerTaker_ValidInputWithoutList() {
        String message = "Сообщение";
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        int result = Dialog.answerTaker(message);
        assertEquals(2, result);
    }

    @Test
    void testAnswerTaker_ValidInputWithList() {
        List<Integer> validAnswers = Arrays.asList(1, 3, 5, 7);
        String message = "Сообщение";
        String simulatedInput = "2\nab\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        int result = Dialog.answerTaker(message, validAnswers);
        assertEquals(5, result);
        String output = outContent.toString();
        assertTrue(output.contains("Некорректные данные. Повторите ввод."));
    }
}
