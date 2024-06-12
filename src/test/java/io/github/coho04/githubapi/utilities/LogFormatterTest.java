package io.github.coho04.githubapi.utilities;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogFormatterTest {

    private String removeAnsiCodes(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    @Test
    void shouldFormatLogRecordCorrectly() {
        LogFormatter formatter = new LogFormatter();
        LogRecord record = new LogRecord(Level.INFO, "Test message");
        record.setSourceClassName("io.github.coho04.githubapi.utilities.LogFormatterTest");
        record.setMillis(System.currentTimeMillis());

        String result = formatter.format(record);
        String expectedStart = "\u001B[34m[";
        String expectedEnd = "\u001B[0m\n";

        assertEquals(expectedStart, result.substring(0, expectedStart.length()));
        assertEquals(expectedEnd, result.substring(result.length() - expectedEnd.length()));

        // Remove ANSI codes from the result
        result = removeAnsiCodes(result);

        // Verify the level name
        String[] resultParts = result.split(" ");
        String levelName = resultParts[3].replace("[", "").replace("]", "").trim();
        assertEquals(Level.INFO.getName(), levelName);

        // Extract and verify the message
        int messageStartIndex = result.indexOf(" - ") + 3;
        String message = result.substring(messageStartIndex).trim();
        assertEquals("Test message", message);
    }

    @Test
    void shouldFormatLogRecordWithParametersCorrectly() {
        LogFormatter formatter = new LogFormatter();
        LogRecord record = new LogRecord(Level.INFO, "Test message {0} {1}");
        record.setSourceClassName("io.github.coho04.githubapi.utilities.LogFormatterTest");
        record.setMillis(System.currentTimeMillis());
        record.setParameters(new Object[]{"param1", "param2"});

        String result = formatter.format(record);

        assertTrue(result.contains("[INFO]"));
        assertTrue(result.contains("Test message"));
    }
}
