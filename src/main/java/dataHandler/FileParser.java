package dataHandler;

import Service.Utils;
import logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class that parse txt stats
 *
 *      At this moment 10 countries with the max Covid-19 cases:
 *         USA, India, Brazil, UK, Russia, Turkey, France, Iran, Germany, Argentina
 *      Moreover let's add stats about following countries:
 *         Israel, Switzerland, Japan, China
 *      And will look at the entire stats of all the world
 *
 *      We use stats from https://www.worldometers.info/coronavirus/
 *      starts at 15.02.2020
 *      data was cached at 22.11.2021
 *
 *
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class FileParser {
    /** Private constructor to avoid creating objects of this class*/
    private FileParser() {}

    /** Optional countries that user can choose from ComboBox */
    public static final String[] optionalCountries = {"usa", "india", "brazil", "uk", "russia", "turkey", "france", "iran",
            "germany", "argentina", "israel"};

    /** Required countries that will be shown at graph */
    public static final String[] requiredCountries = {"switzerland", "japan", "china", "world"};

    /** Array of txt filenames*/
    public static final String[] fileNames = Utils.UnionArrays(optionalCountries, requiredCountries);

    /** Prefix for txt file names */
    private static final String prefix = "stats" + File.separator;

    /** Postfix for txt file names */
    private static final String postfix = ".txt";

    /**
     * Read data from files
     * @return HashMap of type <countryName, List of cases>
     * @throws IOException if it is some problems with files
     */
    public static Map<String, List<Integer>> ReadDataFromFiles() throws IOException {
        Map<String, List<Integer>> stats = new HashMap<>();

        // read data from all the files
        for (var fileName: fileNames)
        {
            // check if file exists
            String fullName = prefix + fileName + postfix;
            File file = new File(fullName);
            if (!file.exists())
            {
                throw new FileNotFoundException("File " + fullName + " not found");
            }

            // read data from file
            String content = Files.lines(Paths.get(fullName)).findFirst().orElse(null);

            // check is reading is successful
            if (content == null)
            {
                throw  new IOException("File " + fullName + " exists, but have problems with data");
            }

            // split string to array of string
            var stringArray = content.split(",");

            // transform string array to int representation
            var convertedList = ConvertStringArrayToIntList(stringArray);

            stats.put(fileName, convertedList);
        }

        Logger.writeInfo("Data was successfully loaded from files");

        return stats;
    }

    /**
     * Convert string array to list of integer
     * @param data List of string representations of numbers
     * @return List of integers
     */
    private static List<Integer> ConvertStringArrayToIntList(String[] data)
    {
        List<Integer> convertedList = new ArrayList<>();

        for (var string : data)
        {
            convertedList.add(Integer.parseInt(string));
        }

        return convertedList;
    }


}
