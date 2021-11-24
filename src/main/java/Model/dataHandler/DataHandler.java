package Model.dataHandler;

import Model.configuration.ConfigManager;
import Model.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that handle data
 * Using patterns: SINGLETON
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class DataHandler {
    /** Private constructor to avoid creating instance avoid getInstance()*/
    private DataHandler() {
        GetData();
        HandleData();
    }

    /** The only instance of <i>DataHandler</i> class*/
    private static DataHandler instance = null;

    /** Array for storing data from files*/
    private static Map<String, List<Integer>> rowData = new HashMap<>();

    /** Array for storing reduced data (every STEP days)*/
    private static final Map<String, List<Integer>> reducedData = new HashMap<>();

    /** Array for storing data about difference between week cases*/
    private static final Map<String, List<Integer>> diffForReducedPeriod = new HashMap<>();

    /** Method to get instance of <i>DataHandler</i> class
     * @see DataHandler#instance
     * @return The only instance of <i>DataHandler</i>
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();

        return instance;
    }

    /**
     * Read data from files
     */
    private void GetData() {
        try
        {
            rowData = FileParser.ReadDataFromFiles();
        }
        catch (Exception exp)
        {
            Logger.writeError(exp.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Reduce data and calculate difference between week cases
     */
    private void HandleData()
    {
        ReduceArray();

        for (var country : FileParser.fileNames)
        {
            List<Integer> diffsArray = new ArrayList<>();
            for (int i = 1; i < reducedData.get(country).size(); i++)
            {
                var diff = reducedData.get(country).get(i) - reducedData.get(country).get(i-1);
                diffsArray.add(diff);
            }
            diffForReducedPeriod.put(country, diffsArray);
            int lastIndex = reducedData.get(country).size() - 1;
            reducedData.get(country).remove(lastIndex);
        }

        Logger.writeInfo("Data was calculated");
    }

    /**
     * Reduce array to every STEP days
     */
    private void ReduceArray()
    {
        // will analyze only every STEP (from Config) days
        int step = ConfigManager.getStep();
        for (var country: FileParser.fileNames)
        {
            List<Integer> reducedArray = new ArrayList<>();
            for (int i = 0; i < rowData.get(country).size(); i += step)
            {
                var endIndex = Math.min(i + step, rowData.get(country).size());

                long sum = 0L;
                for (int j = i; j < endIndex; j++)
                {
                    sum += rowData.get(country).get(j);
                }

                int averagePerWeek = (int)Math.round((double)sum / (endIndex - i));

                reducedArray.add(averagePerWeek);
            }
            reducedData.put(country, reducedArray);
        }

        Logger.writeInfo("Data was reduced with step " + ConfigManager.getStep());
    }

    /**
     * Getter for diffForReducePeriod
     * @see DataHandler#diffForReducedPeriod
     * @return diffForReducePeriod
     */
    public Map<String, List<Integer>> getDiffForReducedPeriod()
    {
        return diffForReducedPeriod;
    }

    /**
     * Getter for reducedData
     * @see DataHandler#reducedData
     * @return reduceData
     */
    public Map<String, List<Integer>> getReducedData()
    {
        return reducedData;
    }
}
