package logger;

import configuration.ConfigManager;

import java.io.FileWriter;

/**
 * Class for log info into log file
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class Logger {
    /** Hidden constructor to deny creating object */
    private Logger() {}

    /**
     * Write info to the log file
     * @param level message level of LogLevel
     * @param message comment that should be written
     */
    private static void writeToLog(LogLevel level, String message)
    {
        try {
            String text =  level + " " + DataTimeUtils.GetCurrentDateTime() + " " + message + "\n";

            var fw = new FileWriter(ConfigManager.getLogFileName(), true);
            fw.write(text);
            fw.close();
        }
        catch (Exception exception) {
            System.out.println("Error with logs: " + exception.getMessage());
        }

    }

    /**
     * Write FATAL error to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String) 
     */
    public static void writeFatal(String message) {
        writeToLog(LogLevel.FATAL, message);
    }

    /**
     * Write ERROR to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeError(String message) {
        writeToLog(LogLevel.ERROR, message);
    }

    /**
     * Write WARN to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeWarn(String message) {
        writeToLog(LogLevel.WARN, message);
    }

    /**
     * Write INFO to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeInfo(String message) {
        writeToLog(LogLevel.INFO, message);
    }

    /**
     * Write DEBUG to the log file (only if DEBUG mode is ON in config)
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeDebug(String message) {
        if (ConfigManager.getIsDebugMode())
            writeToLog(LogLevel.DEBUG, message);
    }
}