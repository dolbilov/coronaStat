package configuration;

/**
 * Class that contains current settings
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class Config {
    /**Is DEBUG mode enabled */
    Boolean isDebugMode;

    /** FileName for logs */
    String logFileName;

    /** How many days will be reduced to one*/
    int step;

    /**
     * Package visible constructor to create default Config
     */
    Config()
    {
        isDebugMode = false;
        logFileName = "info.log";
        step = 30;
    }
}
