package configuration;

/**
 * Class that contains current settings
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class Config {
    /**Is DEBUG mode enabled */
    Boolean isDebugMode = false;

    /** FileName for logs */
    String logFileName = "info.log";

    /** How many days will be reduced to one*/
    int step = 14;
}
