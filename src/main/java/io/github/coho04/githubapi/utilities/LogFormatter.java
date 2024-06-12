package io.github.coho04.githubapi.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This class extends the Formatter class to provide a custom format for logging messages.
 * The format includes the timestamp, source class name, log level, and the log message.
 * If there are any parameters associated with the log record, they are also included in the log message.
 */
public class LogFormatter extends Formatter
{
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_WHITE = "\u001B[37m";

    /**
     * This method formats the log record as per the custom format.
     * @param record The log record to be formatted.
     * @return The formatted log record as a string.
     */
    @Override
    public String format(LogRecord record)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(ANSI_BLUE);

        builder.append("[");
        builder.append(calcDate(record.getMillis()));
        builder.append("]");

        builder.append(" [");
        String fullClassName = record.getSourceClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        builder.append(className);

        builder.append("]");

        builder.append(" [");
        builder.append(record.getLevel().getName());
        builder.append("]");

        builder.append(ANSI_WHITE);
        builder.append(" - ");
        builder.append(record.getMessage());

        Object[] params = record.getParameters();

        if (params != null)
        {
            builder.append("\t");
            for (int i = 0; i < params.length; i++)
            {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    /**
     * This method converts the given milliseconds to a date string in the format "yyyy-MM-dd HH:mm:ss".
     * @param milliseconds The milliseconds to be converted to a date string.
     * @return The date string.
     */
    private String calcDate(long milliseconds) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(milliseconds);
        return date_format.format(resultdate);
    }
}