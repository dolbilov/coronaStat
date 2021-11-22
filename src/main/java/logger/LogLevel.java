package logger;


/**
 * ENUM class contains levels of logs
 * LEVELS:
 * - FATAL — critical error, app can't keep working
 * - ERROR — import error, but app can keep working
 * - WARN — things that's doesn't look right, but app work as usual
 * - INFO — just message that can be helpful
 * - DEBUG — debug info (only if DEBUG mode is ON in config)
 * @author Kirill Dolbilov
 * @version 1.0
 */
public enum LogLevel {
    FATAL,
    ERROR,
    WARN,
    INFO,
    DEBUG;

    /**
     * Method to get formatted string representation of LogLevel value
     */
    @Override
    public String toString()
    {
        return "[" + super.toString() + "]";
    }
}
