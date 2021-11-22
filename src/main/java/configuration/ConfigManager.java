package configuration;

import com.google.gson.Gson;

import java.io.*;
import java.lang.module.FindException;


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

    /**FileWriter object to save data to the config file*/
    private static FileWriter fileWriter;

    /**Check is config was initialized to prevent using with initialization*/
    public static Boolean isInitialized = false;

    /** private constructor to deny creating <i>ConfigManager</i> objects*/
    private ConfigManager() {}

    /** Current config */
    public static Config currentConfig = null;

    /**
     * Initialize config from file
     * @throws IOException if file have wrong format
     * @throws FindException if file not found
     */
    public static void Initialize() throws IOException {
        isInitialized = true;

        if (!f.exists())
            throw new FindException("File with settings not found");

        String data = new BufferedReader(new FileReader(f)).readLine();
        var gson = new Gson();
        currentConfig = gson.fromJson(data, Config.class);
    }



    /**
     * Getter for <i>isDebugMode</i>
     * @see Config#isDebugMode
     * @return Current value of <i>isDebugMode</i>
     * @throws IllegalAccessException if config was not initialized
     */
    public static Boolean getIsDebugMode() throws IllegalAccessException {
        if (!isInitialized)
            throw new IllegalAccessException("Config should be initialized before using");
        return currentConfig.isDebugMode;
    }


    /**
     * Getter for <i>logFileName</i>
     * @see Config#logFileName
     * @return Current value of <i>logFileName</i>
     * @throws IllegalAccessException if config was not initialized
     */
    public static String getLogFileName() throws IllegalAccessException {
        //if (!isInitialized)
            //throw new IllegalAccessException("Config should be initialized before using");
        return currentConfig.logFileName;
    }
}
