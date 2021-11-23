package dataHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileParser {
    private FileParser() {}

    /*
     At this moment 10 countries with the max Covid-19 cases:
        USA, India, Brazil, UK, Russia, Turkey, France, Iran, Germany, Argentina
     Moreover let's add stats about following countries:
        Israel, Switzerland, Japan, China
     And will look at the entire stats of all the world

     We use stats from https://www.worldometers.info/coronavirus/
     starts at 15.02.2020
     data was cached at 22.11.2021
     */

    public static final String[] optionalCountries = {"usa", "india", "brazil", "uk", "russia", "turkey", "france", "iran",
            "germany", "argentina", "israel"};

    public static final String[] requiredCountries = {"switzerland", "japan", "china", "world"};

    public static final String[] fileNames = UnionArrays(optionalCountries, requiredCountries);

    private static final String prefix = "stats" + File.separator;
    private static final String postfix = ".txt";

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

        return stats;
    }

    private static List<Integer> ConvertStringArrayToIntList(String[] data)
    {
        List<Integer> convertedList = new ArrayList<>();

        for (var string : data)
        {
            convertedList.add(Integer.parseInt(string));
        }

        return convertedList;
    }

    private static String[] UnionArrays(String[] first, String[] second)
    {
        String[] result = new String[first.length + second.length];
        int ind = 0;
        for (String s : first) result[ind++] = s;
        for (String s : second) result[ind++] = s;

        return result;
    }
}
