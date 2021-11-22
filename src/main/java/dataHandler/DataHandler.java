package dataHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataHandler {
    private DataHandler() throws IOException {
        GetData();
        HandleData();
    }

    private static DataHandler instance = null;
    private static Map<String, List<Integer>> rowData = new HashMap<>();
    private static Map<String, List<Integer>> handledData = new HashMap<>();

    public static DataHandler getInstance() throws IOException {
        if (instance == null)
            instance = new DataHandler();

        return instance;
    }

    private static void GetData() throws IOException {
        rowData = FileParser.ReadDataFromFiles();
    }

    // TODO: create handle function
    private static void HandleData()
    {
        handledData = null;
    }


}
