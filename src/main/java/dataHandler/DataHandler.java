package dataHandler;

import configuration.ConfigManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    private DataHandler() throws IOException {
        GetData();
        HandleData();
    }

    private static DataHandler instance = null;
    private static Map<String, List<Integer>> rowData = new HashMap<>();
    private static final Map<String, List<Integer>> reducedData = new HashMap<>();
    private static final Map<String, List<Integer>> diffForReducedPeriod = new HashMap<>();

    public static DataHandler getInstance() throws IOException {
        if (instance == null)
            instance = new DataHandler();

        return instance;
    }

    private void GetData() throws IOException {
        rowData = FileParser.ReadDataFromFiles();
    }

    // TODO: create handle function
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
    }

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

                int sum = 0;
                for (int j = i; j < endIndex; j++)
                {
                    sum += rowData.get(country).get(j);
                }

                int averagePerWeek = (int)Math.round((double)sum / (endIndex - i));

                reducedArray.add(averagePerWeek);
            }
            reducedData.put(country, reducedArray);
        }
    }

    public Map<String, List<Integer>> getDiffForReducedPeriod()
    {
        return diffForReducedPeriod;
    }

    public Map<String, List<Integer>> getReducedData()
    {
        return reducedData;
    }


}
