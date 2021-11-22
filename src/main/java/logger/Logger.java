package logger;

import configuration.ConfigManager;

import java.io.FileWriter;
import java.io.IOException;

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
    private static void writeToLog(LogLevel level, String message) throws IOException, IllegalAccessException {
        String text =  level + " " + DataTimeUtils.GetCurrentDateTime() + " " + message + "\n";

        var fw = new FileWriter(ConfigManager.getLogFileName());
        fw.write(text);
        fw.close();
    }

    /**
     * Write FATAL error to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String) 
     */
    public static void writeFatal(String message) throws IOException, IllegalAccessException {
        writeToLog(LogLevel.FATAL, message);
    }

    /**
     * Write ERROR to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeError(String message) throws IOException, IllegalAccessException {
        writeToLog(LogLevel.ERROR, message);
    }

    /**
     * Write WARN to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeWarn(String message) throws IOException, IllegalAccessException {
        writeToLog(LogLevel.WARN, message);
    }

    /**
     * Write INFO to the log file
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeInfo(String message) throws IOException, IllegalAccessException {
        writeToLog(LogLevel.INFO, message);
    }

    /**
     * Write DEBUG to the log file (only if DEBUG mode is ON in config)
     * @param message Message that should be written to the log file
     * @see Logger#writeToLog(LogLevel, String)
     */
    public static void writeDebug(String message) throws IOException, IllegalAccessException {
        if (ConfigManager.getIsDebugMode())
            writeToLog(LogLevel.DEBUG, message);
    }
}