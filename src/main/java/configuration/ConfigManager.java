package configuration;

import com.google.gson.Gson;
import logger.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Class to manage <i>Config</i> instance
 * @see Config

 * @author Kirill Dolbilov
 * @version 1.0
 */
public class ConfigManager {
    /**File name that contains app settings*/
    private static final String filename = "settings.json";

    /**Contains info about config file*/
    private static final File f = new File(filename);

    /**Check is config was initialized to prevent using with initialization*/
    public static Boolean isInitialized = false;

    /** private constructor to deny creating <i>ConfigManager</i> objects*/
    private ConfigManager() {}

    /** Current config */
    public static Config currentConfig = new Config();

    /**
     * Initialize config from file
     * @throws IOException if file have wrong format
     * @throws FindException if file not found
     */
    public static void Initialize() throws IOException {
        if (!f.exists())
        {
            Logger.writeFatal("Config file not found");
            throw new FindException("File with settings not found");
        }

        StringBuilder sb = new StringBuilder();
        Files.lines(Paths.get(filename)).forEach(sb::append);
        String data = sb.toString();

        var gson = new Gson();
        currentConfig = gson.fromJson(data, Config.class);

        isInitialized = true;
        Logger.writeInfo("Config was initialized from file");
    }

    /**
     * Getter for <i>isDebugMode</i>
     * @see Config#isDebugMode
     * @return Current value of <i>isDebugMode</i>
     */
    public static Boolean getIsDebugMode() {
        return currentConfig.isDebugMode;
    }


    /**
     * Getter for <i>logFileName</i>
     * @see Config#logFileName
     * @return Current value of <i>logFileName</i>
     */
    public static String getLogFileName() {
        return currentConfig.logFileName;
    }


    /**
     * Getter for <i>step</i>
     * @see Config#step
     * @return Current value of step
     */
    public static int getStep()
    {
        return currentConfig.step;
    }
}
